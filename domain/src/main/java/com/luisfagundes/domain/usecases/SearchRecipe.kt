package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.coroutines.IoDispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class SearchRecipe @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    data class Params(
        val query: String,
    )

    suspend operator fun invoke(params: Params) = withContext(dispatcher) {
        repository.searchRecipeList(params.query)
    }
}