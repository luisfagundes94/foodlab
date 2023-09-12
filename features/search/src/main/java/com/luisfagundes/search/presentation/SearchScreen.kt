package com.luisfagundes.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.FoodlabSearchBar
import com.luisfagundes.domain.models.MealCategory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.VideoGuide
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.components.MealCategoryGrid
import com.luisfagundes.search.components.VideoGuides


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

    LaunchedEffect(Unit) {
        viewModel.fetchVideoGuides()
    }
}

@Composable
internal fun SearchScreen(
    onRecipeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    uiState: SearchUiState
) {
    var query by rememberSaveable { mutableStateOf("") }
    var searching by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        FoodlabSearchBar(
            query = query,
            searching = searching,
            placeholderHint = stringResource(R.string.search_recipes),
            onQueryChange = {
                searching = true
                query = it
            },
            onClear = {
                searching = false
                query = ""
            },
            onSearch = {}
        )

        if (searching) {
            SearchScreenResults(
                modifier = modifier,
                recipes = uiState.recipes
            )
        } else {
            SearchScreenContent(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                videoGuideList = uiState.videoGuides,
                onRecipeClick = onRecipeClick
            )
        }
    }
}


@Composable
private fun SearchScreenContent(
    modifier: Modifier = Modifier,
    videoGuideList: List<VideoGuide>,
    onRecipeClick: (id: Int) -> Unit,
) {
    val mealCategories = MealCategory.entries
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
    ) {
        MealCategoryGrid(
            mealCategories = mealCategories,
            onClick = {}
        )
        VideoGuides(
            title = stringResource(R.string.video_guides),
            videoGuideList = videoGuideList,
            onVideoClick = {
                if (it.isNotEmpty()) {
                    uriHandler.openUri("https://www.youtube.com/watch?v=$it")
                }
            }
        )
    }
}

@Composable
internal fun SearchScreenResults(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(recipes) { recipe ->
            Text(text = recipe.title)
        }
    }
}


