package com.luisfagundes.recipe.data.models

import com.luisfagundes.data.responses.MeasuresResponse

data class IngredientResponse(
    val id: Int,
    val amount: Float,
    val name: String,
    val image: String?,
    val measures: MeasuresResponse,
    val original: String?
)