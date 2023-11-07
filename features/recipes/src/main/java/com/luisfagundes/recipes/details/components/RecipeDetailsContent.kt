package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luisfagundes.components.HtmlText
import com.luisfagundes.domain.enums.IngredientUnit
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.recipes.details.presentation.RecipeDetailsUiState
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun RecipeDetailsScreenContent(
    uiState: RecipeDetailsUiState.Success,
    modifier: Modifier,
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
            steps = recipe.instructions?.firstOrNull()?.steps ?: emptyList()
        )
    }
}