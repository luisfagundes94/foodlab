package com.luisfagundes.recipes.details.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.luisfagundes.components.ErrorView
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.components.HtmlText
import com.luisfagundes.components.SavingRecipeToast
import com.luisfagundes.domain.enums.IngredientUnit
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.features.recipes.R
import com.luisfagundes.recipes.details.components.Ingredients
import com.luisfagundes.recipes.details.components.RecipeFacts
import com.luisfagundes.recipes.details.components.RecipeImage
import com.luisfagundes.recipes.details.components.RecipeSteps
import com.luisfagundes.recipes.details.components.SpanWithLink
import com.luisfagundes.resources.theme.spacing

@Composable
fun RecipeDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SavingRecipeToast(viewModel.saveRecipeEvent)

    RecipeDetailsScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize(),
        onBackClick = onBackClick,
        onRetryClick = viewModel::refreshRecipeDetails,
        onTopBarActionClick = viewModel::saveRecipe,
    )

    LaunchedEffect(Unit) {
        viewModel.refreshRecipeDetails()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RecipeDetailsScreen(
    uiState: RecipeDetailsUiState,
    modifier: Modifier,
    onTopBarActionClick: (recipe: Recipe?) -> Unit = {},
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit = {},
) {

    val recipe = (uiState as? RecipeDetailsUiState.Success)?.recipe

    FoodlabTopAppBar(
        titleRes = R.string.recipe_details_title,
        navigationIcon = Icons.Default.ArrowBack,
        navigationIconContentDescription = stringResource(R.string.back),
        actionIcon = Icons.Default.BookmarkAdd,
        onActionClick = { onTopBarActionClick(recipe) },
        actionIconContentDescription = stringResource(R.string.save_recipe),
        onNavigationClick = onBackClick,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            val internalModifier = Modifier
                .padding(MaterialTheme.spacing.default)
                .align(Alignment.CenterHorizontally)

            when (uiState) {
                is RecipeDetailsUiState.Idle -> Unit
                is RecipeDetailsUiState.Loading -> CircularProgressIndicator(
                    modifier = internalModifier
                )

                is RecipeDetailsUiState.Error -> ErrorView(
                    modifier = internalModifier,
                    message = stringResource(R.string.recipe_details_error),
                    onRetryClick = onRetryClick
                )

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
    var servings by remember { mutableIntStateOf(recipe.serves) }

    val isPreview = LocalInspectionMode.current

    Column(
        modifier = modifier
    ) {
        RecipeImage(
            isPreview = isPreview,
            recipe = recipe
        )
        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.small),
            text = recipe.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        RecipeFacts(
            modifier = Modifier
                .fillMaxWidth()
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
                .padding(vertical = MaterialTheme.spacing.verySmall),
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
        RecipeSteps(
            steps = recipe.instructions?.first()?.steps ?: emptyList()
        )
    }
}