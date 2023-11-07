package com.luisfagundes.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.FoodlabSearchBar
import com.luisfagundes.components.LoadingView
import com.luisfagundes.core.utils.doNothing
import com.luisfagundes.domain.enums.MealType
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.components.SearchResults
import com.luisfagundes.search.components.StaticContent


@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onRecipeClick: (id: String) -> Unit,
    onMealTypeClick: (MealType) -> Unit,
) {
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val videoGuideUiState by viewModel.videoGuideUiState.collectAsStateWithLifecycle()

    SearchScreen(
        onRecipeClick = onRecipeClick,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.default)
            .padding(top = MaterialTheme.spacing.verySmall),
        searchUiState = searchUiState,
        videoGuideUiState = videoGuideUiState,
        onQueryChange = viewModel::fetchSearchResults,
        onSearch = viewModel::fetchSearchResults,
        onClear = viewModel::setNotSearchingState,
        onMealTypeClick = onMealTypeClick,
    )

    LaunchedEffect(Unit) {
        viewModel.fetchVideoGuides()
    }
}

@Composable
internal fun SearchScreen(
    onRecipeClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    videoGuideUiState: VideoGuideUiState,
    onQueryChange: (query: String) -> Unit = {},
    onSearch: (query: String) -> Unit = {},
    onClear: () -> Unit = {},
    onMealTypeClick: (MealType) -> Unit = {},
) {
    var query by rememberSaveable { mutableStateOf("") }
    var showClearButton by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        FoodlabSearchBar(
            query = query,
            searching = showClearButton,
            placeholderHint = stringResource(R.string.search_recipes),
            onQueryChange = {
                onQueryChange(it)
                query = it
                showClearButton = it.isNotEmpty()
            },
            onClear = {
                onClear()
                query = ""
                showClearButton = false
            },
            onSearch = onSearch
        )

        when (searchUiState) {
            is SearchUiState.Success -> SearchResults(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MaterialTheme.spacing.default),
                recipes = searchUiState.recipes,
                onRecipeClick = onRecipeClick,
            )

            is SearchUiState.Loading -> LoadingView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = MaterialTheme.spacing.default)
            )

            is SearchUiState.Searching -> doNothing()

            is SearchUiState.NotSearching -> StaticContent(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                videoGuideUiState = videoGuideUiState,
                onMealTypeClick = onMealTypeClick,
            )

            is SearchUiState.Empty -> Text(
                text = stringResource(R.string.empty)
            )

            is SearchUiState.Error -> Text(
                text = stringResource(R.string.error)
            )
        }
    }
}