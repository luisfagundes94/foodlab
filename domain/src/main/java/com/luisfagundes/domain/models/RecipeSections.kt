package com.luisfagundes.domain.models

data class RecipeSections(
    val popularRecipes: List<Recipe>,
    val healthyRecipes: List<Recipe>,
    val quickRecipes: List<Recipe>
)
