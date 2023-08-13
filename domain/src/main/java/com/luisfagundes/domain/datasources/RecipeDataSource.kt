package com.luisfagundes.domain.datasources

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.recipe.domain.models.RecipeListBody

interface RecipeDataSource {
    suspend fun fetchRecipes(params: Map<String, String>): RecipeListBody
    suspend fun fetchRecipeDetails(id: Int): Recipe
    suspend fun fetchRecipesAutoComplete(query: String): List<RecipeAutoComplete>
}
