package com.luisfagundes.recipes.details.presentation

sealed class RecipeEvent {
    data class RecipeSaved(val isBookmarked: Boolean) : RecipeEvent()
    data class RecipeDeleted(val isBookmarked: Boolean) : RecipeEvent()
    data object Error : RecipeEvent()
}