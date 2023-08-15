package com.luisfagundes.domain.models

data class Ingredient(
    val id: Int,
    val name: String,
    val amount: Float,
    val imageUrl: String?,
    val measures: Measures,
    val originalMeasure: String
)
