package com.luisfagundes.home.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.FoodlabThemePreview

@Composable
@FoodlabThemePreview
fun HomeScreenPreview() {
    val uiState = HomeUiState.Error
    FoodlabTheme {
        Surface {
            HomeScreen(
                uiState = uiState,
                modifier = Modifier,
                onRecipeClick = {},
                onFavoriteClick = {},
            )
        }
    }
}