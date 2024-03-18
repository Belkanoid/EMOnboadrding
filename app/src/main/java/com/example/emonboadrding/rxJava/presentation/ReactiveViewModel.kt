package com.example.emonboadrding.rxJava.presentation

import androidx.lifecycle.ViewModel
import com.example.emonboadrding.rxJava.data.ReactiveRepositoryImpl
import com.example.emonboadrding.rxJava.domain.DiscountCard
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class ReactiveViewModel : ViewModel() {

    private val reactiveRepository = ReactiveRepositoryImpl()

    fun performSomeTask(key: Long) = reactiveRepository.getSomeData(abs(key).toString())

    fun getTimer(delay: Long = 1): Observable<Long> = Observable.interval(delay, TimeUnit.SECONDS)

    fun getDiscountCards(isAllDiscountCardRequired: Boolean = true): Single<List<DiscountCard>> =
        reactiveRepository.getDiscountCards(isAllDiscountCardRequired)


}