package com.luisfagundes.remote.datasources

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.remote.mappers.RecipeAutoCompleteMapper.toDomainModel
import com.luisfagundes.remote.mappers.RecipeMapper.toDomainModel
import com.luisfagundes.remote.utils.getJsonDataFromAsset
import com.luisfagundes.domain.models.RecipeAutoComplete
import com.luisfagundes.recipe.domain.models.RecipeListBody
import com.luisfagundes.recipe.data.models.RecipeAutoCompleteResponse
import com.luisfagundes.recipe.data.models.RecipeBodyResponse
import com.luisfagundes.recipe.data.models.RecipeResponse
import com.luisfagundes.domain.models.Recipe

class MockedRecipeDataSource(
    private val appContext: Context
) : RecipeDataSource {
    override suspend fun fetchRecipes(params: Map<String, String>): RecipeListBody {
        val jsonFile = getJsonDataFromAsset(appContext, "recipes.json")
        val gson = Gson()
        val data = gson.fromJson(jsonFile, RecipeBodyResponse::class.java)
        val recipes = data.results.map { it.toDomainModel() }

        return RecipeListBody(
            data.offset,
            data.totalResults,
            recipes
        )
    }

    override suspend fun fetchRecipeDetails(id: Int): Recipe {
        val jsonFile = getJsonDataFromAsset(appContext, "recipe_detail.json")
        val gson = Gson()
        val data = gson.fromJson(jsonFile, RecipeResponse::class.java)
        return data.toDomainModel()
    }

    override suspend fun fetchRecipesAutoComplete(query: String): List<RecipeAutoComplete> {
        val jsonFile = getJsonDataFromAsset(appContext, "recipe_autocomplete.json")
        val gson = Gson()
        val listType = object : TypeToken<List<RecipeAutoCompleteResponse>>() {}.type
        val data: List<RecipeAutoCompleteResponse> = gson.fromJson(jsonFile, listType)
        return data.toDomainModel()
    }
}
