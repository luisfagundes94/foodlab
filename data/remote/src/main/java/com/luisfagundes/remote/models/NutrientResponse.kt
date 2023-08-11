package com.luisfagundes.data.responses

data class NutrientResponse(
    val name: String,
    val amount: Float,
    val unit: String,
    val percentOfDailyNeeds: Float
)
