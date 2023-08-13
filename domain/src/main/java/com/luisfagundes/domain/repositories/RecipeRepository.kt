package com.luisfagundes.domain.repositories

import androidx.paging.PagingSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.domain.usecases.GetRecipeList

interface RecipeRepository {
    fun getFlowRecipeList(params: GetFlowRecipeList.Params): PagingSource<Int, Recipe>
    suspend fun getRecipeList(params: GetRecipeList.Params): List<Recipe>
}
