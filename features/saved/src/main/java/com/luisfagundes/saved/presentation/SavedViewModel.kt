package com.luisfagundes.saved.presentation

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: RecipeRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<SavedUiState>(SavedUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun getSavedRecipes() = safeLaunch {
        _uiState.update { SavedUiState.Loading }

        val recipes = repository.getSavedRecipeList()

        _uiState.update {
            recipes.takeIf { it.isNotEmpty() }
                ?.let { SavedUiState.Success(it) }
                ?: SavedUiState.Empty
        }
    }

    fun deleteRecipe(recipe: Recipe) = safeLaunch {
        repository.deleteRecipe(recipe)
    }
}
