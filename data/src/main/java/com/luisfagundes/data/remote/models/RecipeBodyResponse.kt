package com.luisfagundes.data.remote.models

data class RecipeBodyResponse(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RecipeResponse>
)
