package com.luisfagundes.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.luisfagundes.domain.models.MealCategory
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.mapper.MealCategoryMapper.mapToUiModel
import com.luisfagundes.search.uimodel.MealCategoryUiModel

private const val MEAL_CATEGORY_GRID_COLUMNS = 2

@Composable
fun MealCategoryGrid(
    mealCategories: List<MealCategory>,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier.padding(top = MaterialTheme.spacing.default),
        text = stringResource(R.string.search_by_category),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge
    )
    val rows = mealCategories.chunked(MEAL_CATEGORY_GRID_COLUMNS)
    Column {
        rows.forEach { rowCategories ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = MaterialTheme.spacing.verySmall,
                    alignment = Alignment.CenterHorizontally
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.default)
            ) {
                rowCategories.forEach { category ->
                    MealCategory(
                        modifier = Modifier.weight(1f),
                        uiModel = category.mapToUiModel(),
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MealCategory(
    modifier: Modifier = Modifier,
    uiModel: MealCategoryUiModel,
    onClick: () -> Unit
) {
    val title = stringResource(id = uiModel.titleResId)
    val image = painterResource(id = uiModel.imageResId)

    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = title
            )
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.verySmall),
                text = title,
                color = Color.White,
            )
        }
    }
}