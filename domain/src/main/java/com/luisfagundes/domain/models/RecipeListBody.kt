package com.luisfagundes.recipe.domain.models

import com.luisfagundes.domain.models.Recipe

data class RecipeListBody(
    val offset: Int,
    val totalResults: Int,
    val results: List<Recipe>
)
