package com.example.emonboadrding.kotlin.task3


inline fun <reified T> List<Any>.firstElement(): T? = find { it is T } as? T

fun List<Any>.firstInt(): Int? = this.firstElement<Int>()

//fun List<Any>.firstInt(): Int? = find { it is Int } as? Int