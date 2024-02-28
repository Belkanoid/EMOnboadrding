package com.example.emonboadrding.kotlin.task4

import org.junit.Assert
import org.junit.Test


class AnotherExtensionsKtTest {

    @Test
    fun `test ShakerSort`() {
        //arrangement
        val list1 = listOf(3, 1, null, 5, null, 2, 4, null)
        val list2 = listOf<Int?>()

        //action
        val sortedList1 = list1.shakerSort()
        val sortedList2 = list2.shakerSort()

        //assert
        Assert.assertEquals(listOf(1, 2, 3, 4, 5, null, null, null), sortedList1)
        Assert.assertEquals(listOf<Int?>(), sortedList2)
    }
}