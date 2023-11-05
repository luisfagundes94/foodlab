package com.luisfagundes.saved.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.DeletingRecipeToast
import com.luisfagundes.components.RecipeItem
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun SavedRoute(
    viewModel: SavedViewModel = hiltViewModel(),
    onRecipeClick: (id: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DeletingRecipeToast(viewModel.deleteEvent)

    SavedScreen(
        modifier = Modifier.fillMaxSize(),
        uiState = uiState,
        onRecipeClick = onRecipeClick,
        onDeleteClick = viewModel::deleteRecipe,
    )

    LaunchedEffect(Unit) {
        viewModel.getSavedRecipes()
    }
}

@Composable
internal fun SavedScreen(
    modifier: Modifier = Modifier,
    uiState: SavedUiState,
    onRecipeClick: (id: String) -> Unit = {},
    onDeleteClick: (recipe: Recipe) -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is SavedUiState.Idle -> Unit
            is SavedUiState.Loading -> CircularProgressIndicator(
                Modifier.padding(MaterialTheme.spacing.default)
            )
            is SavedUiState.Empty -> Text(text = "Empty")
            is SavedUiState.Success -> SavedContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.default),
                recipes = uiState.recipes,
                onRecipeClick = onRecipeClick,
                onDeleteClick = onDeleteClick,
            )
        }
    }
}

@Composable
private fun SavedContent(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (id: String) -> Unit,
    onDeleteClick: (recipe: Recipe) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onRecipeClick(recipe.id.toString()) },
                showDeleteIcon = true,
                title = recipe.title,
                imageUrl = recipe.imageUrl,
                onIconClick = { onDeleteClick(recipe) },
            )
        }
    }
}
