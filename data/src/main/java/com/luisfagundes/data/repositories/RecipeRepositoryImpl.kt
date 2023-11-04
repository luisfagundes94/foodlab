package com.luisfagundes.data.repositories

import androidx.paging.PagingSource
import com.luisfagundes.data.remote.paging.RecipePagingSource
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.framework.network.safeApiCall
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
    ) = safeApiCall {
        recipeDataSource.fetchRecipes(
            params = mapOf(
                NUMBER to DEFAULT_PAGE_SIZE.toString(),
                SORT to params.sort,
            )
        ).results
    }

    override suspend fun getRecipeDetails(id: Int) = safeApiCall {
        recipeDataSource.fetchRecipeDetails(id)
    }

    override suspend fun searchRecipes(query: String) = safeApiCall {
        recipeDataSource.fetchRecipes(
            params = mapOf(
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
        const val QUERY = "query"
    }
}
