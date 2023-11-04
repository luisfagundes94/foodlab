package com.luisfagundes.saved.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SavedRoute(
    viewModel: SavedViewModel = hiltViewModel(),
    onRecipeClick: (id: Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
}

@Composable
internal fun SavedScreen(
    modifier: Modifier = Modifier,
    uiState: SavedUiState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is SavedUiState.Idle -> Unit
            is SavedUiState.Loading -> {
                CircularProgressIndicator()
            }
            is SavedUiState.Empty -> {
                // TODO
            }
            is SavedUiState.Success -> {
                // TODO
            }
        }
    }
}
