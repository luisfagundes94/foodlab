package com.luisfagundes.recipes.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.components.HtmlText
import com.luisfagundes.domain.enums.IngredientUnit
import com.luisfagundes.features.recipes.R
import com.luisfagundes.recipe.domain.factory.RecipeFactory
import com.luisfagundes.recipes.details.components.Ingredients
import com.luisfagundes.recipes.details.components.ExtraInfo
import com.luisfagundes.recipes.details.components.RecipeSteps
import com.luisfagundes.recipes.details.components.SpanWithLink
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.ThemePreviews
import com.luisfagundes.resources.theme.spacing

private const val BOTTOM_CORNER_PERCENT = 5

@Composable
fun RecipeDetailsRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RecipeDetailsScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize(),
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    modifier: Modifier,
    onBackClick: () -> Unit,
) {

    FoodlabTopAppBar(
        titleRes = R.string.recipe_details_title,
        navigationIcon = Icons.Default.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.back),
        actionIcon = Icons.Default.BookmarkAdd,
        actionIconContentDescription = stringResource(R.string.save_recipe),
        onNavigationClick = onBackClick,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            when (uiState) {
                is RecipeDetailsUiState.Loading -> CircularProgressIndicator()
                is RecipeDetailsUiState.Error -> Text(stringResource(R.string.recipe_details_error))
                is RecipeDetailsUiState.Success -> RecipeDetailsScreenContent(
                    uiState = uiState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.default)
                )
            }
        }
    }
}

@Composable
internal fun RecipeDetailsScreenContent(
    uiState: RecipeDetailsUiState.Success,
    modifier: Modifier
) {
    val recipe = uiState.recipe
    var ingredientUnit by remember { mutableStateOf(IngredientUnit.US) }
    var servings by remember { mutableIntStateOf(recipe.servings) }

    Column(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        bottomEndPercent = BOTTOM_CORNER_PERCENT,
                        bottomStartPercent = BOTTOM_CORNER_PERCENT,
                    )
                ),
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
        )
        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small),
            text = recipe.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        ExtraInfo(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.secondary,
                        )
                    ),
                    shape = MaterialTheme.shapes.medium,
                )
                .padding(vertical = MaterialTheme.spacing.verySmall)
                .fillMaxSize(),
            recipe = recipe,
        )
        HtmlText(
            modifier = Modifier.padding(top = MaterialTheme.spacing.default),
            text = recipe.summary,
            color = MaterialTheme.colorScheme.onSurface,
        )
        SpanWithLink(
            modifier = Modifier.padding(top = MaterialTheme.spacing.verySmall),
            text = "Source: ${recipe.sourceName}",
            linkColor = MaterialTheme.colorScheme.secondary,
            url = recipe.sourceUrl ?: "",
        )
        Ingredients(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.default)
                .fillMaxWidth(),
            ingredients = recipe.ingredients,
            servings = servings,
            onUnitChange = { newIngredientUnit ->
                ingredientUnit = newIngredientUnit
            },
            unit = ingredientUnit,
            onAddServingsClick = { servings++ },
            onRemoveServingsClick = {
                if (servings > 1) servings--
            },
        )
        Text(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.default),
            text = stringResource(id = R.string.steps),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        RecipeSteps(
            steps = recipe.instructions.first().steps
        )
    }
}

@Composable
@ThemePreviews
internal fun RecipeDetailsScreenPreview() {
    val fakeRecipe = RecipeFactory.create()
    val uiState = RecipeDetailsUiState.Success(fakeRecipe)

    FoodlabTheme {
        RecipeDetailsScreenContent(
            uiState = uiState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}