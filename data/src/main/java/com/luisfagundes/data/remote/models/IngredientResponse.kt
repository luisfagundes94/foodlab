package com.luisfagundes.data.remote.models

data class IngredientResponse(
    val id: Int,
    val amount: Float,
    val name: String,
    val image: String?,
    val measures: MeasuresResponse,
    val original: String?
)