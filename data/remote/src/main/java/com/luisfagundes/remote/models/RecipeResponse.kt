package com.luisfagundes.recipe.data.models

import com.luisfagundes.data.responses.InstructionResponse

data class RecipeResponse(
    val id: Int,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val sourceUrl: String?,
    val aggregateLikes: Int,
    val spoonacularScore: Int,
    val sourceName: String?,
    val healthScore: Int,
    val cheap: Boolean,
    val extendedIngredients: List<IngredientResponse>?,
    val vegetarian: Boolean,
    val vegan: Boolean,
    val dishTypes: List<String>?,
    val summary: String?,
    val glutenFree: Boolean?,
    val dairyFree: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val sustainable: Boolean?,
    val analyzedInstructions: List<InstructionResponse>?,
)