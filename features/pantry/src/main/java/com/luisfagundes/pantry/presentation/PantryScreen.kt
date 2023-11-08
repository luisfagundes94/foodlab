package com.luisfagundes.pantry.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.domain.factory.FakePantryFactory
import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.features.pantry.R
import com.luisfagundes.pantry.components.PantryCategory
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.FoodlabThemePreview
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun PantryRoute(
    viewModel: PantryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PantryScreen(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.default),
        uiState = uiState,
    )

    LaunchedEffect(Unit) {
        viewModel.fetchCommonPantryItems()
    }
}

@Composable
internal fun PantryScreen(
    modifier: Modifier = Modifier,
    uiState: PantryUiState,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.absoluteOffset(y = -MaterialTheme.spacing.default),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.padding(MaterialTheme.spacing.verySmall),
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.desc_add_ingredient)
                )
            }
        },
        content = { contentPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(contentPadding),
            ) {
                Text(
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.verySmall),
                    text = stringResource(R.string.pantry_headline)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.verySmall)
                        .align(alignment = Alignment.CenterHorizontally),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = stringResource(R.string.btn_find_recipes)
                    )
                }

                when (uiState) {
                    is PantryUiState.Success -> PantryContent(
                        categories = uiState.categories
                    )

                    is PantryUiState.Error -> Text(
                        text = stringResource(R.string.error_loading_pantry_items)
                    )

                    is PantryUiState.Loading -> CircularProgressIndicator()
                    else -> Unit
                }
            }
        }
    )

}

@Composable
internal fun PantryContent(
    categories: List<PantryCategory>
) {
    LazyColumn {
        items(categories) { category ->
            PantryCategory(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.verySmall),
                category = category
            )
        }
    }
}

@Composable
@FoodlabThemePreview
internal fun PantryScreenPreview() {
    val uiState = PantryUiState.Success(FakePantryFactory.categories)

    FoodlabTheme {
        Surface {
            PantryScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.default),
                uiState = uiState,
            )
        }
    }
}
