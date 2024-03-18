package com.example.emonboadrding.rxJava.domain

import java.util.UUID

data class DiscountCard(
    val name: String,
    val id: String = UUID.randomUUID().toString(),
)
