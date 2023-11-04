package com.luisfagundes.data.remote.models

data class NutrientResponse(
    val name: String,
    val amount: Float,
    val unit: String,
    val percentOfDailyNeeds: Float
)
