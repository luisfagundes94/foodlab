package com.luisfagundes.recipes.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.features.recipes.R
import com.luisfagundes.framework.extension.fromHtml
import com.luisfagundes.recipe.domain.factory.RecipeFactory
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.ThemePreviews

@Composable
fun RecipeDetailsRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecipeDetailsScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize(),
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    modifier: Modifier,
    onBackClick: () -> Unit,
) {

    FoodlabTopAppBar(
        titleRes = R.string.recipe_details_title,
        navigationIcon = Icons.Default.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.back),
        actionIcon = Icons.Default.BookmarkAdd,
        actionIconContentDescription = stringResource(R.string.save_recipe),
        onNavigationClick = onBackClick,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            when (uiState) {
                is RecipeDetailsUiState.Loading -> CircularProgressIndicator()
                is RecipeDetailsUiState.Error -> Text(stringResource(R.string.recipe_details_error))
                is RecipeDetailsUiState.Success -> RecipeDetailsScreenContent(
                    uiState = uiState,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
internal fun RecipeDetailsScreenContent(
    uiState: RecipeDetailsUiState.Success,
    modifier: Modifier
) {
    val recipe = uiState.recipe

    Column(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
        )
        Text(text = recipe.title)
        Text(text = buildAnnotatedString {
            recipe.summary.fromHtml()
        })
    }
}

@Composable
@ThemePreviews
internal fun RecipeDetailsScreenPreview() {
    val fakeRecipe = RecipeFactory.create()
    val uiState = RecipeDetailsUiState.Success(fakeRecipe)

    FoodlabTheme {
        RecipeDetailsScreenContent(
            uiState = uiState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}