package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.enums.RecipeSortingOptions
import com.luisfagundes.domain.models.RecipeSections
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.coroutines.IoDispatcher
import com.luisfagundes.framework.network.Result
import com.luisfagundes.framework.network.getResultOrThrow
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
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

            val popularRecipes = repository.getRecipeList(popularParams).getResultOrThrow()
            val healthyRecipes = repository.getRecipeList(healthyParams).getResultOrThrow()
            val quickRecipes = repository.getRecipeList(quickParams).getResultOrThrow()

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
            Timber.d(e)
            emit(Result.Error(e))
        }
    }.flowOn(dispatcher)

    private fun createGetRecipeListParams(sort: String) = GetRecipeList.Params(
        sort = sort,
    )
}
