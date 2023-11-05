package com.luisfagundes.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.FoodlabSearchBar
import com.luisfagundes.components.LoadingView
import com.luisfagundes.core.utils.doNothing
import com.luisfagundes.domain.models.MealCategory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.components.MealCategoryGrid
import com.luisfagundes.search.components.SearchResults
import com.luisfagundes.search.components.VideoGuides

private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="


@Composable
internal fun SearchRoute(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
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
    )

    LaunchedEffect(Unit) {
        viewModel.fetchVideoGuides()
    }
}

@Composable
internal fun SearchScreen(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    videoGuideUiState: VideoGuideUiState,
    onQueryChange: (query: String) -> Unit = {},
    onSearch: (query: String) -> Unit = {},
    onClear: () -> Unit = {},
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
                onFavoriteClick = {},
            )
            is SearchUiState.Loading -> LoadingView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = MaterialTheme.spacing.default)
            )
            is SearchUiState.Searching -> doNothing()

            is SearchUiState.NotSearching -> SearchScreenContent(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                videoGuideUiState = videoGuideUiState,
                onRecipeClick = onRecipeClick
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


@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    videoGuideUiState: VideoGuideUiState,
    onRecipeClick: (id: Int) -> Unit,
) {
    val mealCategories = MealCategory.entries
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier,
    ) {
        MealCategoryGrid(
            mealCategories = mealCategories,
            onClick = {}
        )
        when (videoGuideUiState) {
            is VideoGuideUiState.Loading -> LoadingView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = MaterialTheme.spacing.default)
            )

            is VideoGuideUiState.Error -> Text(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.default),
                text = stringResource(R.string.error)
            )

            is VideoGuideUiState.Success -> VideoGuides(
                title = stringResource(R.string.video_guides),
                videoGuideList = videoGuideUiState.videoGuideList,
                onVideoClick = {
                    if (it.isNotEmpty()) {
                        uriHandler.openUri("$YOUTUBE_BASE_URL$it")
                    }
                }
            )
        }
    }
}


