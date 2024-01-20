package com.luisfagundes.ingredients.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.FoodlabSearchBar
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.domain.models.PantryItem
import com.luisfagundes.features.pantry.R
import com.luisfagundes.framework.extension.empty
import com.luisfagundes.ingredients.components.AddIngredientContent
import com.luisfagundes.resources.theme.spacing

@Composable
fun AddIngredientRoute(
    viewModel: AddIngredientViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedIngredients by viewModel.selectedIngredients.collectAsStateWithLifecycle()

    AddIngredientScreen(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.default),
        uiState = uiState,
        selectedIngredients = selectedIngredients,
        onBackClick = onBackClick,
        onSelectedIngredient = viewModel::addIngredientToList,
        onDeselectedIngredient = viewModel::removeIngredientFromList,
    )

    LaunchedEffect(Unit) {
        viewModel.getPantryCategories()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddIngredientScreen(
    modifier: Modifier = Modifier,
    uiState: AddIngredientUiState,
    selectedIngredients: List<PantryItem>,
    onBackClick: () -> Unit = {},
    onSearch: (query: String) -> Unit = {},
    onSelectedIngredient: (ingredient: PantryItem) -> Unit = {},
    onDeselectedIngredient: (ingredient: PantryItem) -> Unit = {},
) {
    var query by rememberSaveable { mutableStateOf("") }
    var showClearButton by rememberSaveable { mutableStateOf(false) }

    FoodlabTopAppBar(
        titleRes = R.string.add_ingredient_title,
        navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
        onNavigationClick = onBackClick,
    ) {
        Scaffold(
            floatingActionButton = {
                Button(
                    enabled = selectedIngredients.isNotEmpty(),
                    onClick = { /*TODO*/ }
                ) {
                    Text("Confirm Selection")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { paddingValues ->
            Column(
                modifier = modifier.padding(paddingValues),
            ) {
                FoodlabSearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    placeholderHint = stringResource(R.string.search_to_add_ingredients),
                    query = query,
                    onQueryChange = { currentQuery ->
                        query = currentQuery
                        showClearButton = currentQuery.isNotEmpty()
                    },
                    onClear = {
                        query = String.empty()
                        showClearButton = false
                    },
                    onSearch = onSearch,
                )
                when (uiState) {
                    is AddIngredientUiState.Loading -> CircularProgressIndicator()
                    is AddIngredientUiState.Error -> Text(stringResource(R.string.error_fetching_ingredients))
                    is AddIngredientUiState.Success -> AddIngredientContent(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        categories = uiState.categories,
                        selectedIngredients = selectedIngredients,
                        onSelectedIngredient = onSelectedIngredient,
                        onDeselectedIngredient = onDeselectedIngredient,
                    )
                }
            }
        }
    }
}