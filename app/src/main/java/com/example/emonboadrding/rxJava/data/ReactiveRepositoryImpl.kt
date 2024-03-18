package com.example.emonboadrding.rxJava.data

import com.example.emonboadrding.rxJava.domain.DiscountCard
import com.example.emonboadrding.rxJava.domain.ReactiveRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ReactiveRepositoryImpl(
    private val api: RemoteApi = RemoteApi()
) : ReactiveRepository {
    override fun getSomeData(someKey: String) = api.getSomeDataByKey(someKey)
        .subscribeOn(Schedulers.io())

    override fun getDiscountCards(isAllDiscountCardRequired: Boolean) =
        Single.zip(
            api.getDiscountCardsFromApiV1(false).requireNotEmptyList(isAllDiscountCardRequired),
            api.getDiscountCardsFromApiV2().requireNotEmptyList(isAllDiscountCardRequired)
        ) { list1, list2 -> list1 + list2 }
            .onErrorReturnItem(listOf())
}

private fun Single<List<DiscountCard>>.requireNotEmptyList(isRequired: Boolean) =
    this.onErrorReturn { if (isRequired) throw it else listOf() }