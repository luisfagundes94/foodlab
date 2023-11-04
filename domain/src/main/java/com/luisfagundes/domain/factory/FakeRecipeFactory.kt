package com.luisfagundes.domain.factory

import com.luisfagundes.domain.models.Ingredient
import com.luisfagundes.domain.models.Instruction
import com.luisfagundes.domain.models.Measure
import com.luisfagundes.domain.models.Measures
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeSections
import com.luisfagundes.domain.models.Step
import com.luisfagundes.recipe.domain.models.RecipeListBody

private const val RANDOM_NUMBER = 1
private const val TITLE = "Omelete de Queijo"
private const val IMAGE = "IMAGE"

object FakeRecipeFactory {
    val recipe = Recipe(
        id = RANDOM_NUMBER,
        title = TITLE,
        imageUrl = IMAGE,
        serves = RANDOM_NUMBER,
        readyInMinutes = RANDOM_NUMBER,
        sourceUrl = "",
        aggregateLikes = RANDOM_NUMBER,
        spoonacularScore = RANDOM_NUMBER,
        healthScore = RANDOM_NUMBER,
        cheap = false,
        ingredients = listOf(
            Ingredient(
                id = RANDOM_NUMBER,
                name = "Ovo",
                imageUrl = IMAGE,
                amount = 1.0f,
                measures = Measures(
                    metric = Measure(
                        amount = 1.0f,
                        unitLong = "unidade",
                        unitShort = "un",
                    ),
                    us = Measure(
                        amount = 1.0f,
                        unitLong = "unidade",
                        unitShort = "un",
                    ),
                ),
                originalMeasure = "1",
            ),
            Ingredient(
                id = RANDOM_NUMBER,
                name = "Ovo",
                imageUrl = IMAGE,
                amount = 1.0f,
                measures = Measures(
                    metric = Measure(
                        amount = 1.0f,
                        unitLong = "unidade",
                        unitShort = "un",
                    ),
                    us = Measure(
                        amount = 1.0f,
                        unitLong = "unidade",
                        unitShort = "un",
                    ),
                ),
                originalMeasure = "1",
            ),
        ),
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
        instructions = listOf(
            Instruction(
                name = "Instruction",
                steps = listOf(
                    Step(
                        number = RANDOM_NUMBER,
                        step = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            " when an unknown printer took a galley of type and scrambled it to make a type" +
                            " specimen book. It has survived not only five centuries, but also the leap into" +
                            " electronic typesetting, remaining essentially unchanged. It was popularised in" +
                            " the 1960s with the release of Letraset sheets containing Lorem Ipsum passages," +
                            " and more recently with desktop publishing software like Aldus PageMaker " +
                            "including versions of Lorem Ipsum.",
                    )
                )
            )
        )
    )

    val sections = RecipeSections(
        popularRecipes = listOf(recipe, recipe),
        healthyRecipes = listOf(recipe, recipe),
        quickRecipes = listOf(recipe, recipe),
    )

    val recipeListBody = RecipeListBody(
        offset = RANDOM_NUMBER,
        totalResults = 2,
        results = listOf(recipe, recipe)
    )
}