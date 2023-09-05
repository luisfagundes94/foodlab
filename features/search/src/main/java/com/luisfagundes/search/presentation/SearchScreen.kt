package com.luisfagundes.search.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.domain.models.MealCategory
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.components.MealCategory
import com.luisfagundes.search.mapper.MealCategoryMapper.mapToUiModel
import com.luisfagundes.search.uimodel.MealCategoryUiModel

private const val MEAL_CATEGORY_GRID_COLUMNS = 2

@Composable
internal fun SearchRoute(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        onRecipeClick = onRecipeClick,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.default)
            .padding(top = MaterialTheme.spacing.verySmall),
        uiState = uiState,
    )
}

@Composable
internal fun SearchScreen(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    uiState: SearchUiState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is SearchUiState.Loading -> CircularProgressIndicator()
            is SearchUiState.Success -> SearchScreenContent(onRecipeClick)
            is SearchUiState.Error -> TODO()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenContent(
    onRecipeClick: (id: Int) -> Unit,
) {
    val mealCategories = MealCategory.entries
    var query by rememberSaveable { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    DockedSearchBar(
        query = query,
        onQueryChange = { query = it },
        placeholder = { Text(stringResource(R.string.search_recipes)) },
        onSearch = { /*TODO*/ },
        active = active,
        onActiveChange = { active = it }
    ) {

    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(MEAL_CATEGORY_GRID_COLUMNS),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.spacing.verySmall,
            alignment = Alignment.CenterHorizontally
        ),
        content = {
            items(mealCategories.size) { index ->
                MealCategory(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.default),
                    uiModel = mealCategories[index].mapToUiModel(),
                    onClick = { /*TODO*/ }
                )
            }
        }
    )
}
