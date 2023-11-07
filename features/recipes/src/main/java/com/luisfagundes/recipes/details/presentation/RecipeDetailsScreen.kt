package com.luisfagundes.recipes.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.ErrorView
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.components.HandleDeletedRecipeEvent
import com.luisfagundes.components.HandleSavedRecipeEvent
import com.luisfagundes.features.recipes.R
import com.luisfagundes.recipes.details.components.RecipeDetailsScreenContent
import com.luisfagundes.resources.theme.spacing

@Composable
fun RecipeDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isBookmarked by viewModel.isBookmarked.collectAsStateWithLifecycle()
    val recipe = (uiState as? RecipeDetailsUiState.Success)?.recipe

    HandleSavedRecipeEvent(viewModel.saveRecipeEvent)
    HandleDeletedRecipeEvent(viewModel.deleteRecipeEvent) { onBackClick.invoke() }

    RecipeDetailsScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize(),
        onBackClick = onBackClick,
        onRetryClick = viewModel::refreshRecipeDetails,
        isBookmarked = isBookmarked,
        onBookmarkToggle = { viewModel.handleRecipeEvent(isBookmarked, recipe) }
    )

    LaunchedEffect(Unit) {
        viewModel.refreshRecipeDetails()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    modifier: Modifier,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit = {},
) {
    var bookmarked by remember { mutableStateOf<Boolean?>(null) }
    var icon by remember { mutableStateOf<ImageVector?>(null) }

    FoodlabTopAppBar(
        titleRes = R.string.recipe_details_title,
        navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
        navigationIconDescription = stringResource(R.string.back),
        actionIcon = if (isBookmarked) Icons.Filled.Delete else Icons.Filled.BookmarkAdd,
        onActionClick = onBookmarkToggle,
        actionIconContentDescription = stringResource(R.string.save_recipe),
        onNavigationClick = onBackClick,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            val internalModifier = Modifier
                .padding(MaterialTheme.spacing.default)
                .align(Alignment.CenterHorizontally)

            when (uiState) {
                is RecipeDetailsUiState.Idle -> Unit
                is RecipeDetailsUiState.Loading -> CircularProgressIndicator(
                    modifier = internalModifier
                )

                is RecipeDetailsUiState.Error -> ErrorView(
                    modifier = internalModifier,
                    message = stringResource(R.string.recipe_details_error),
                    onRetryClick = onRetryClick
                )

                is RecipeDetailsUiState.Success -> RecipeDetailsScreenContent(
                    uiState = uiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.default),
                )
            }
        }
    }
}