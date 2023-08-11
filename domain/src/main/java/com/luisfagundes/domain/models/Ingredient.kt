package com.luisfagundes.domain.models

data class Ingredient(
    val id: Int,
    val name: String,
    val amount: Float,
    val image: String?,
    val measures: Measures,
    val originalMeasure: String
)
