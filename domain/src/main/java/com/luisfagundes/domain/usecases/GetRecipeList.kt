package com.luisfagundes.domain.usecases

import com.luisfagundes.framework.coroutines.IoDispatcher
import com.luisfagundes.domain.repositories.RecipeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetRecipeList @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    data class Params(
        val sort: String,
    )

    suspend operator fun invoke(params: Params) = withContext(dispatcher) {
        repository.getRecipeList(params)
    }
}
