package com.luisfagundes.remote.datasources

import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.remote.mappers.RecipeAutoCompleteMapper.toDomainModel
import com.luisfagundes.remote.mappers.RecipeMapper.toDomainModel
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.recipe.domain.models.RecipeListBody
import com.luisfagundes.remote.service.RecipeService

class RemoteRecipeDataSource(
    private val service: RecipeService
) : RecipeDataSource {
    override suspend fun fetchRecipes(params: Map<String, String>): RecipeListBody {
        val data = service.fetchRecipes(params)
        val recipes = data.results.map { it.toDomainModel() }

        return RecipeListBody(
            data.offset,
            data.totalResults,
            recipes
        )
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        val data = service.fetchRecipeDetails(id)
        return data.toDomainModel()
    }

    override suspend fun fetchRecipesAutoComplete(query: String): List<RecipeAutoComplete> {
        val data = service.fetchRecipesAutoComplete(query = query)
        return data.toDomainModel()
    }
}
