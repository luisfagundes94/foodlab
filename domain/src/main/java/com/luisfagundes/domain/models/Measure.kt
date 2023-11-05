package com.luisfagundes.domain.models

data class Measure(
    val id: Int,
    val amount: Float,
    val unitLong: String,
    val unitShort: String
)