package com.luisfagundes.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.luisfagundes.components.SavingRecipeToast
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.foodlab.features.home.R
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun HomeRoute(
    onRecipeClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavingRecipeToast(viewModel.saveRecipeEvent)

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
    onRecipeClick: (id: String) -> Unit,
    onFavoriteClick: (recipe: Recipe) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (uiState) {
            is HomeUiState.Loading -> CircularProgressIndicator()
            is HomeUiState.Error -> HomeScreenError(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            is HomeUiState.Success -> HomeScreenContent(
                uiState = uiState,
                modifier = modifier,
                onRecipeClick = onRecipeClick,
                onFavoriteClick = onFavoriteClick,
            )
        }
    }
}

@Composable
private fun HomeScreenError(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.home_screen_error),
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState.Success,
    modifier: Modifier,
    onRecipeClick: (id: String) -> Unit,
    onFavoriteClick: (recipe: Recipe) -> Unit,
) {
    val verticalSpacing = MaterialTheme.spacing.verySmall

    Column(
        modifier = modifier,
    ) {
        HomeRecipeSection(
            modifier = Modifier.padding(bottom = verticalSpacing),
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