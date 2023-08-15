package com.luisfagundes.domain.factory

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeSections

private const val RANDOM_NUMBER = 1
private const val TITLE = "Omelete de Queijo"
private const val IMAGE = "IMAGE"

object FakeRecipeFactory {
    val recipe = Recipe(
        id = RANDOM_NUMBER,
        title = TITLE,
        imageUrl = IMAGE,
        servings = RANDOM_NUMBER,
        readyInMinutes = RANDOM_NUMBER,
        sourceUrl = "",
        aggregateLikes = RANDOM_NUMBER,
        spoonacularScore = RANDOM_NUMBER,
        healthScore = RANDOM_NUMBER,
        cheap = false,
        ingredients = emptyList(),
        vegetarian = false,
        vegan = false,
        dishTypes = emptyList(),
        summary = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                " when an unknown printer took a galley of type and scrambled it to make a type" +
                " specimen book. It has survived not only five centuries, but also the leap into" +
                " electronic typesetting, remaining essentially unchanged. It was popularised in" +
                " the 1960s with the release of Letraset sheets containing Lorem Ipsum passages," +
                " and more recently with desktop publishing software like Aldus PageMaker " +
                "including versions of Lorem Ipsum.",
        sourceName = "",
        glutenFree = true,
        dairyFree = false,
        veryPopular = false,
        veryHealthy = false,
        sustainable = true,
        instructions = emptyList()
    )

    fun createSections() = RecipeSections(
        popularRecipes = listOf(recipe, recipe),
        healthyRecipes = listOf(recipe, recipe),
        quickRecipes = listOf(recipe, recipe),
    )
}