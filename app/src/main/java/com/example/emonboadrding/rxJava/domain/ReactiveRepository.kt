package com.example.emonboadrding.rxJava.domain

import io.reactivex.Single

interface ReactiveRepository {

    fun getSomeData(someKey: String): Single<String>

    fun getDiscountCards(isAllDiscountCardRequired: Boolean = true): Single<List<DiscountCard>>

}