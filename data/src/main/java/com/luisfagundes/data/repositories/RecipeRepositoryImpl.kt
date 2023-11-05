package com.luisfagundes.data.repositories

import androidx.paging.PagingSource
import com.luisfagundes.data.local.database.FoodlabDatabase
import com.luisfagundes.data.remote.mappers.RecipeMapper.toDomainModel
import com.luisfagundes.data.remote.mappers.RecipeMapper.toEntityModel
import com.luisfagundes.data.remote.paging.RecipePagingSource
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.framework.network.Result
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.framework.network.safeApiCall
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dataSource: RecipeDataSource,
    private val database: FoodlabDatabase,
) : RecipeRepository {
    override fun getFlowRecipeList(
        params: GetFlowRecipeList.Params
    ): PagingSource<Int, Recipe> =
        RecipePagingSource(
            recipeDataSource = dataSource,
            queryMap = mapOf(
                NUMBER to DEFAULT_PAGE_SIZE.toString(),
                ADD_RECIPE_INFORMATION to true.toString(),
                LIMIT_LICENSE to true.toString(),
                INSTRUCTIONS_REQUIRED to true.toString(),
                SORT to params.sort
            )
        )

    override fun getSavedRecipeList(): List<Recipe> {
        return database.recipeDao().getAll().map { it.toDomainModel() }
    }

    override fun saveRecipe(recipe: Recipe): Result<Unit> {
        val result = database.recipeDao().insert(recipe.toEntityModel())
        val exception = Exception("Error saving recipe")

        return if (result > 0) Result.Success(Unit) else Result.Error(exception)
    }

    override fun deleteRecipe(recipe: Recipe): Result<Unit> {
        val result = database.recipeDao().delete(recipe.toEntityModel())
        val exception = Exception("Error deleting recipe")

        return if (result == 1) Result.Success(Unit) else Result.Error(exception)
    }

    override suspend fun getRecipeList(
        params: GetRecipeList.Params
    ) = safeApiCall {
        dataSource.fetchRecipes(
            params = mapOf(
                NUMBER to DEFAULT_PAGE_SIZE.toString(),
                SORT to params.sort,
            )
        ).results
    }

    override suspend fun getRecipeDetails(id: Int) = safeApiCall {
        val recipeEntity = database.recipeDao().getById(id)
        recipeEntity?.toDomainModel() ?: dataSource.fetchRecipeDetails(id)
    }

    override suspend fun searchRecipes(query: String) = safeApiCall {
        dataSource.fetchRecipes(
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
