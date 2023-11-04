package com.luisfagundes.pantry.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.luisfagundes.pantry.presentation.PantryViewModel

@Composable
internal fun PantryRoute(
    viewModel: PantryViewModel = hiltViewModel()
) {

    PantryScreen(
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
internal fun PantryScreen(
    modifier: Modifier = Modifier,
) {
    Column {

    }
}
