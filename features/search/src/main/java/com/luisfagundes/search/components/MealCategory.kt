package com.luisfagundes.search.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.uimodel.MealCategoryUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealCategory(
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
                painter = image,
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