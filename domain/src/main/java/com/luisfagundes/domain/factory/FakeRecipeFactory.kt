package com.luisfagundes.domain.factory

import com.luisfagundes.domain.models.Recipe

object FakeRecipeFactory {
    val recipe = Recipe(
        id = 1,
        title = "Pasta Carbonara",
        imageUrl = "imageUrl",
        servings = 1,
        readyInMinutes = 1,
        sourceUrl = "sourceUrl",
        aggregateLikes = 1,
        spoonacularScore = 1,
        healthScore = 1,
        cheap = true,
        ingredients = listOf(),
        vegetarian = true,
        vegan = true,
        dishTypes = listOf(),
        summary = "summary",
        sourceName = "sourceName",
        glutenFree = true,
        dairyFree = true,
        veryHealthy = true,
        veryPopular = true,
        sustainable = true,
        instructions = listOf()
    )
}