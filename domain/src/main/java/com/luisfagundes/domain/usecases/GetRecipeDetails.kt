package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.coroutines.IoDispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import com.luisfagundes.framework.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class GetRecipeDetails @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    data class Params(
        val id: Int
    )
    operator fun invoke(params: Params) = flow {
        emit(Result.Loading)
        emit(repository.getRecipeDetails(params.id))
    }.flowOn(dispatcher)
}