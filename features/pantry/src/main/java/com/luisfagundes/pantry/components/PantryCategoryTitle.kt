package com.luisfagundes.pantry.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import com.luisfagundes.domain.models.PantryCategory

@Composable
fun pantryCategoryTitle(category: PantryCategory) = buildAnnotatedString {
    withStyle(
        style = SpanStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize)
    ) {
        append(category.name)
    }
    withStyle(
        style = SpanStyle(
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            baselineShift = BaselineShift(0.1f)
        )
    ) {
        append("  ●  ")
    }
    withStyle(
        style = SpanStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)
    ) {
        append(category.items.size.toString())
    }
}