package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.coroutines.IoDispatcher
import com.luisfagundes.framework.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ToggleRecipeBookmarkStatus @Inject constructor(
    private val repository: RecipeRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    data class Params(
        val recipe: Recipe?,
        val isBookmarked: Boolean,
    )
    suspend operator fun invoke(params: Params): Result<Unit> = withContext(dispatcher) {
        val recipe = params.recipe
        val isBookmarked = params.isBookmarked

        if (recipe == null) {
            Timber.e("Attempted to bookmark a null recipe.")
            return@withContext Result.Error(NullPointerException("Recipe must not be null"))
        }
        return@withContext if (isBookmarked) {
            repository.deleteRecipe(recipe)
        } else {
            repository.saveRecipe(recipe)
        }
    }
}
