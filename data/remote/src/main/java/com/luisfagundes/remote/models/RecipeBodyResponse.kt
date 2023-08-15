package com.luisfagundes.recipe.data.models

import com.luisfagundes.remote.models.RecipeResponse

data class RecipeBodyResponse(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RecipeResponse>
)
