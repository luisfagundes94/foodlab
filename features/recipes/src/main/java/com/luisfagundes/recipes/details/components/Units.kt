package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.luisfagundes.domain.enums.IngredientUnit
import com.luisfagundes.features.recipes.R
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun Units(
    onUnitChange: (IngredientUnit) -> Unit,
    unit: IngredientUnit,
) {
    val defaultColor = MaterialTheme.colorScheme.onSurface
    val unselectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)

    Row {
        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.verySmall)
                .clickable { onUnitChange(IngredientUnit.US) },
            text = stringResource(R.string.us_unit),
            color = if (unit == IngredientUnit.US) defaultColor else unselectedColor,
        )
        Text(
            text = "/"
        )
        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.verySmall)
                .clickable { onUnitChange(IngredientUnit.METRIC) },
            text = stringResource(R.string.metric_unit),
            color = if (unit == IngredientUnit.METRIC) defaultColor else unselectedColor,
        )
    }
}