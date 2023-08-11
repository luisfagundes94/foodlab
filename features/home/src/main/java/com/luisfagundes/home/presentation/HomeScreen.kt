package com.luisfagundes.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.luisfagundes.framework.base.BaseUiState
import com.luisfagundes.recipe.domain.models.Recipe

@Composable
internal fun HomeRoute(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

}

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

}
