package com.example.emonboadrding.rxJava.data

import com.example.emonboadrding.rxJava.domain.DiscountCard
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.UUID

class RemoteApi {

    fun getSomeDataByKey(key: String) = Single.just(
        "$key:\n ${UUID.randomUUID()}"
    )

    fun getDiscountCardsFromApiV1(isErrorOccur: Boolean = false): Single<List<DiscountCard>> {
        if (isErrorOccur) return Single.error(RuntimeException(""))

        return Single.just(
            listOf(
                DiscountCard("A1"),
                DiscountCard("A2"),
                DiscountCard("A3"),
                DiscountCard("A4"),
            )
        ).subscribeOn(Schedulers.io()) // тк сетевые запросы чаще всего работают в io потоке

    }

    fun getDiscountCardsFromApiV2(): Single<List<DiscountCard>> = Single.just(
        listOf(
            DiscountCard("B1"),
            DiscountCard("B2"),
            DiscountCard("B3"),
            DiscountCard("B4"),
        )
    ).subscribeOn(Schedulers.io()) // тк сетевые запросы чаще всего работают в io потоке


}