package com.example.emonboadrding.kotlin.task4

fun List<Int?>.shakerSort(): List<Int?> {
    val mutableList = this.toMutableList()

    var swapped = true
    var start = 0
    var end = mutableList.size - 1

    fun sort(i: Int) {
        if (
            (mutableList[i] == null && mutableList[i + 1] != null) ||
            (mutableList[i] != null && mutableList[i + 1] != null && mutableList[i]!! > mutableList[i + 1]!!)
        ) {
            val temp = mutableList[i]
            mutableList[i] = mutableList[i + 1]
            mutableList[i + 1] = temp
            swapped = true
        }
    }

    while (swapped) {
        swapped = false

        for (i in start until end) { sort(i) }

        if (!swapped) break
        swapped = false
        end -= 1

        for (i in end downTo start) { sort(i) }

        start += 1
    }

    return mutableList
}