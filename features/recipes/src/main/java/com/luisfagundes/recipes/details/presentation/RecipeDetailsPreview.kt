package com.luisfagundes.recipes.details.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.FoodlabThemePreview
import com.luisfagundes.resources.theme.spacing

@Composable
@FoodlabThemePreview
internal fun RecipeDetailsSuccess() {
    val fakeRecipe = FakeRecipeFactory.recipe
    val uiState = RecipeDetailsUiState.Success(fakeRecipe)
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = MaterialTheme.spacing.default)

    FoodlabTheme {
        Surface {
            RecipeDetailsScreenContent(
                modifier = modifier,
                uiState = uiState,
            )
        }
    }
}

@Composable
@FoodlabThemePreview
internal fun RecipeDetailsError() {
    val uiState = RecipeDetailsUiState.Error
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = MaterialTheme.spacing.default)

    FoodlabTheme {
        Surface {
            RecipeDetailsScreen(
                modifier = modifier,
                uiState = uiState,
                onBackClick = {},
            )
        }
    }
}

@Composable
@FoodlabThemePreview
internal fun RecipeDetailsLoading() {
    val uiState = RecipeDetailsUiState.Loading
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = MaterialTheme.spacing.default)

    FoodlabTheme {
        Surface {
            RecipeDetailsScreen(
                modifier = modifier,
                uiState = uiState,
                onBackClick = {},
            )
        }
    }
}