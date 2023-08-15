package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luisfagundes.domain.enums.IngredientUnit
import com.luisfagundes.domain.models.Ingredient
import com.luisfagundes.features.recipes.R
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun Ingredients(
    modifier: Modifier = Modifier,
    ingredients: List<Ingredient>?,
    servings: Int,
    onAddServingsClick: () -> Unit,
    onRemoveServingsClick: () -> Unit,
    onUnitChange: (IngredientUnit) -> Unit,
    unit: IngredientUnit,
) {
    if (ingredients == null) return

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.verySmall)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RecipeServings(
                servings = servings,
                onAddClick = onAddServingsClick,
                onRemoveClick = onRemoveServingsClick,
            )
            IngredientUnits(
                onUnitChange = onUnitChange,
                unit = unit,
            )
        }
        ingredients.forEach { ingredient ->
            IngredientItem(
                ingredient = ingredient,
                ingredientUnit = unit,
            )
        }
    }
}

@Composable
private fun IngredientItem(
    modifier: Modifier = Modifier,
    ingredient: Ingredient,
    ingredientUnit: IngredientUnit,
) {
    val unit = if (ingredientUnit == IngredientUnit.US) ingredient.measures.us.unitLong else ingredient.measures.metric.unitLong
    Row(
        modifier = modifier
            .padding(top = MaterialTheme.spacing.small)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            model = ingredient.imageUrl,
            contentDescription = ingredient.name,
            contentScale = ContentScale.FillBounds,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = MaterialTheme.spacing.small),
            text = ingredient.name,
        )
        Text(
            modifier = Modifier,
            text = "${ingredient.amount} $unit",
        )
    }
}
