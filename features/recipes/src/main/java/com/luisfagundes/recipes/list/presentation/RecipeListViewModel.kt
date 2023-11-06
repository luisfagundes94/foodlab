package com.luisfagundes.recipes.list.presentation

import androidx.lifecycle.SavedStateHandle
import com.luisfagundes.domain.enums.Sort
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.recipes.list.navigation.RecipeListArg
import dagger.hilt.android.lifecycle.HiltViewModel
import com.luisfagundes.framework.network.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipeList: GetRecipeList,
    stringDecoder: StringDecoder,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val recipeListArg = RecipeListArg(savedStateHandle, stringDecoder)
    private val mealType = recipeListArg.mealType

    private val _uiState = MutableStateFlow<RecipeListUiState>(RecipeListUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun fetchRecipeList() = safeLaunch {
        _uiState.update { RecipeListUiState.Loading }
        val params = GetRecipeList.Params(
            sort = Sort.POPULARITY.value,
            type = mealType
        )
        val result = getRecipeList.invoke(params)
        _uiState.update { setState(result) }
    }

    private fun setState(result: Result<List<Recipe>>) = when (result) {
        is Result.Success -> RecipeListUiState.Success(result.data)
        is Result.Error -> RecipeListUiState.Error
        else -> RecipeListUiState.Idle
    }
}