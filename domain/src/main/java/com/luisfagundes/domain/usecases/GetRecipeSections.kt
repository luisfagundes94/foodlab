package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.enums.RecipeSortingOptions
import com.luisfagundes.domain.models.RecipeSections
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.coroutines.IoDispatcher
import com.luisfagundes.framework.network.Result
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class GetRecipeSections @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke() = flow {
        emit(Result.Loading)

        try {
            val popularParams = createGetRecipeListParams(RecipeSortingOptions.POPULAR.value)
            val healthyParams = createGetRecipeListParams(RecipeSortingOptions.HEALTHY.value)
            val quickParams = createGetRecipeListParams(RecipeSortingOptions.QUICK.value)

            val popularRecipes = repository.getRecipeList(popularParams)
            val healthyRecipes = repository.getRecipeList(healthyParams)
            val quickRecipes = repository.getRecipeList(quickParams)

            emit(
                Result.Success(
                    RecipeSections(
                        popularRecipes = popularRecipes,
                        healthyRecipes = healthyRecipes,
                        quickRecipes = quickRecipes
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }.flowOn(dispatcher)

    private fun createGetRecipeListParams(sort: String) = GetRecipeList.Params(
        sort = sort,
    )
}
