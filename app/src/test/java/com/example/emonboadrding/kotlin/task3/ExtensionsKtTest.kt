package com.example.emonboadrding.kotlin.task3

import org.junit.Assert
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun `first int from list of Any`() {
        //arrangement
        val list1 = listOf<Any>("d", "foo", 5.3, 1L, 7f, 8)
        val list2 = listOf<Any>('b', "bar", 5.3, 3, 1L, 7f, 8)
        val list3 = listOf<Any>("another",5.32343, 75,1234L, "foobar",  7f, 90)
        val list4 = listOf<Any>(7f, 1, "d", "bar foo", 5.3, 1L, 9f)
        val list5 = listOf<Any>("d", "dummy text", 5.3, 1L, 7f, 2, 3, 4)
        val list6 = listOf<Any>( 5.3, "d", 92343, "foo text", 32, 1L, 7f, 8)
        val list7 = listOf<Any>(3, 'a', "moo text", 5.3, 1L, 7f, 8)
        val list8 = listOf<Any>("d", "faa", 5.3, 1L, 7f, 8f)

        //action
        val firstInt1 = list1.firstInt()
        val firstInt2 = list2.firstInt()
        val firstInt3 = list3.firstInt()
        val firstInt4 = list4.firstInt()
        val firstInt5 = list5.firstInt()
        val firstInt6 = list6.firstInt()
        val firstInt7 = list7.firstInt()
        val firstInt8 = list8.firstInt()

        //assert
        Assert.assertEquals(8, firstInt1)
        Assert.assertEquals(3, firstInt2)
        Assert.assertEquals(75, firstInt3)
        Assert.assertEquals(1, firstInt4)
        Assert.assertEquals(2, firstInt5)
        Assert.assertEquals(92343, firstInt6)
        Assert.assertEquals(3, firstInt7)
        Assert.assertEquals(null, firstInt8)
    }
}