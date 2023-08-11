package com.luisfagundes.remote.repositories

import androidx.paging.PagingSource
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.framework.network.safeApiCall
import com.luisfagundes.framework.network.DataState
import com.luisfagundes.remote.paging.RecipePagingSource
import com.luisfagundes.recipe.domain.models.Recipe
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDataSource: RecipeDataSource
) : RecipeRepository {
    override fun getFlowRecipeList(
        params: GetFlowRecipeList.Params
    ): PagingSource<Int, Recipe> =
        RecipePagingSource(
            recipeDataSource = recipeDataSource,
            queryMap = mapOf(
                NUMBER to DEFAULT_PAGE_SIZE.toString(),
                ADD_RECIPE_INFORMATION to true.toString(),
                LIMIT_LICENSE to true.toString(),
                INSTRUCTIONS_REQUIRED to true.toString(),
                SORT to params.sort
            )
        )

    override suspend fun getRecipeList(
        params: GetRecipeList.Params
    ): DataState<List<Recipe>> = safeApiCall {
        recipeDataSource.fetchRecipes(
            params = mapOf(
                NUMBER to DEFAULT_PAGE_SIZE.toString(),
                SORT to params.sort,
            )
        ).results
    }

    private companion object {
        const val ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val LIMIT_LICENSE = "limitLicense"
        const val NUMBER = "number"
        const val DEFAULT_PAGE_SIZE = 20
        const val INSTRUCTIONS_REQUIRED = "instructionsRequired"
        const val SORT = "sort"
    }
}
