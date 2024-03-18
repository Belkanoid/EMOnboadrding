package com.example.emonboadrding.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CoroutineTest {

    @Test
    fun test() = runBlocking {
        val handler = CoroutineExceptionHandler { _, ex ->
        }

        val lifecycleScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        lifecycleScope.launch(Dispatchers.IO + handler + SupervisorJob()) {
            launch() {
                while (isActive) {
                    "корутина 1".toLog()
                    delay(500)
                    throw RuntimeException()
                }
            }

            launch() {
                while (isActive) {
                    "корутина 2".toLog()
                    delay(500)
                }
            }
        }
    }

    fun String.toLog() = println(this)

}