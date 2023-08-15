package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import com.luisfagundes.domain.models.Step
import com.luisfagundes.resources.theme.spacing

@Composable
fun RecipeSteps(steps: List<Step>) {
    steps.forEach { step ->
        Step(step)
    }
}

@Composable
private fun Step(step: Step) {
    var isStepDone by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = MaterialTheme.spacing.small)
            .clickable { isStepDone = !isStepDone }
    ) {
        val backgroundNumberColor = MaterialTheme.colorScheme.surfaceVariant
        val backgroundNumberRadius = MaterialTheme.spacing.extraLarge.value

        Text(
            text = if (isStepDone) "✓" else step.number.toString(),
            modifier = Modifier
                .padding(MaterialTheme.spacing.verySmall)
                .drawBehind {
                    drawCircle(
                        color = if (isStepDone) Color.Gray else backgroundNumberColor,
                        radius = backgroundNumberRadius
                    )
                }
        )
        Text(
            text = step.step,
            color = if (isStepDone) Color.Gray else Color.Unspecified,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small)
        )
    }
}