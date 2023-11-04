package com.luisfagundes.domain.repositories

import androidx.paging.PagingSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.framework.network.Result

interface RecipeRepository {
    fun getFlowRecipeList(params: GetFlowRecipeList.Params): PagingSource<Int, Recipe>
    fun getSavedRecipeList(): List<Recipe>
    suspend fun getRecipeList(params: GetRecipeList.Params): Result<List<Recipe>>
    suspend fun getRecipeDetails(id: Int): Result<Recipe>
    suspend fun searchRecipes(query: String): Result<List<Recipe>>
}
