package com.luisfagundes.recipes.list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.features.recipes.R
import com.luisfagundes.recipes.list.components.RecipeList
import com.luisfagundes.resources.theme.spacing

@Composable
fun RecipeListRoute(
    modifier: Modifier = Modifier,
    viewModel: RecipeListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onRecipeClick: (id: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecipeListScreen(
        uiState = uiState,
        modifier = modifier.fillMaxWidth(),
        onBackClick = onBackClick,
        onRecipeClick = onRecipeClick,
        onRetryClick = viewModel::fetchRecipeList,
    )

    LaunchedEffect(Unit) {
        viewModel.fetchRecipeList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeListScreen(
    uiState: RecipeListUiState,
    modifier: Modifier,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    onRecipeClick: (id: String) -> Unit,
) {
    FoodlabTopAppBar(
        titleRes = R.string.recipe_list_title,
        onNavigationClick = onBackClick,
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is RecipeListUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                is RecipeListUiState.Error -> Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.error_loading_recipes),
                )
                is RecipeListUiState.Success -> RecipeList(
                    modifier = Modifier.fillMaxWidth(),
                    recipes = uiState.recipes,
                    onRecipeClick = onRecipeClick,
                )
                else -> Unit
            }
        }
    }
}