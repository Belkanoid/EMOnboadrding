package com.example.emonboadrding.kotlin.task1

/*Могут ли возникнуть какие-то проблемы, если мы будем использовать подобный класс
в качестве ключа для HashMap*/
data class Key(
    val field1: Int,
    var field2: String
) {
    var field3: String? = null
}

//не возникнут, тк для hasMap в качестве ключа используется hashCode объекта.
//В данном случае он генерируется только на основании свойств в первичной коснтрукторе, а они все val


