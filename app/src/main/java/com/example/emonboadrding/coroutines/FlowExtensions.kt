package com.example.emonboadrding.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow


fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    return throttle(periodMillis, 1) { trySend(it) }
}

fun <T> Flow<T>.throttleLast(periodMillis: Long): Flow<T> {
    return throttle(periodMillis, Channel.CONFLATED) { send(it) }
}

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
private fun <T> Flow<T>.throttle(
    periodMillis: Long,
    capacity: Int,
    sendMode: suspend SendChannel<T>.(value: T) -> Unit
): Flow<T> = scopedFlow { downstream ->
    val values = produce(capacity = capacity) { collect { sendMode(it) } }
    val ticker = fixedPeriodTicker(periodMillis)
    for (tick in ticker) {
        values.tryReceive()
            .onSuccess { if(!ticker.isClosedForReceive) downstream.emit(it) }
            .onClosed { ticker.cancel(); return@scopedFlow }
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
internal fun CoroutineScope.fixedPeriodTicker(periodMills: Long): ReceiveChannel<Unit> {
    return produce(capacity = 0) {
        while (true) {
            delay(periodMills)
            send(Unit)
        }

    }
}

internal fun <R> scopedFlow(block: suspend CoroutineScope.(FlowCollector<R>) -> Unit): Flow<R> =
    flow {
        coroutineScope {
            block(this@flow)
        }
    }