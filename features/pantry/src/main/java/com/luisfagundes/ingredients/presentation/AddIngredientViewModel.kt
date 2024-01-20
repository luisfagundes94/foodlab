package com.luisfagundes.ingredients.presentation

import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.domain.models.PantryItem
import com.luisfagundes.domain.repositories.PantryRepository
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import com.luisfagundes.framework.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    private val repository: PantryRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<AddIngredientUiState>(AddIngredientUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _selectedIngredients = MutableStateFlow<List<PantryItem>>(emptyList())
    val selectedIngredients = _selectedIngredients.asStateFlow()

    fun getPantryCategories() = safeLaunch {
        _uiState.update { AddIngredientUiState.Loading }
        val result = repository.fetchPantryCategories()
        handleResult(result)
    }

    private fun handleResult(result: Result<List<PantryCategory>>) {
        when (result) {
            is Result.Success -> _uiState.update { AddIngredientUiState.Success(result.data)  }
            is Result.Error -> _uiState.update { AddIngredientUiState.Error }
            else -> Unit
        }
    }
    fun addIngredientToList(ingredient: PantryItem) {
        _selectedIngredients.update { selectedIngredients ->
            selectedIngredients + ingredient
        }
    }

    fun removeIngredientFromList(ingredient: PantryItem) {
        _selectedIngredients.update { selectedIngredients ->
            selectedIngredients - ingredient
        }
    }

    fun saveIngredients() = safeLaunch {
        //TODO("implement saveIngredients")
    }
}