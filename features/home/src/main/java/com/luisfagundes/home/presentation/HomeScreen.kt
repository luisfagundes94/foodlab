package com.luisfagundes.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.HomeRecipeSection
import com.luisfagundes.foodlab.features.home.R
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun HomeRoute(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        modifier = modifier,
        onRecipeClick = onRecipeClick,
        onFavoriteClick = viewModel::saveRecipe
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier,
    onRecipeClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int) -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is HomeUiState.Loading -> CircularProgressIndicator()
            is HomeUiState.Error -> HomeScreenError()
            is HomeUiState.Success -> HomeScreenContent(
                uiState = uiState,
                modifier = modifier.padding(vertical = MaterialTheme.spacing.default),
                onRecipeClick = onRecipeClick,
                onFavoriteClick = onFavoriteClick,
            )
        }
    }
}

@Composable
private fun HomeScreenError() {
    Text(
        text = stringResource(R.string.home_screen_error),
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState.Success,
    modifier: Modifier,
    onRecipeClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int) -> Unit,
) {
    val verticalSpacing = MaterialTheme.spacing.verySmall

    Column(
        modifier = modifier,
    ) {
        HomeRecipeSection(
            modifier = Modifier.padding(vertical = verticalSpacing),
            sectionTitle = stringResource(R.string.home_screen_popular_recipes),
            recipes = uiState.recipeSections.popularRecipes,
            onFavoriteClick = onFavoriteClick,
            onRecipeClick = onRecipeClick,
        )
        HomeRecipeSection(
            modifier = Modifier.padding(vertical = verticalSpacing),
            sectionTitle = stringResource(R.string.home_screen_healthy_recipes),
            recipes = uiState.recipeSections.healthyRecipes,
            onFavoriteClick = onFavoriteClick,
            onRecipeClick = onRecipeClick,
        )
        HomeRecipeSection(
            modifier = Modifier.padding(vertical = verticalSpacing),
            sectionTitle = stringResource(R.string.home_screen_quick_recipes),
            recipes = uiState.recipeSections.quickRecipes,
            onFavoriteClick = onFavoriteClick,
            onRecipeClick = onRecipeClick,
        )
    }
}