package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
fun SpanWithLink(
    modifier: Modifier = Modifier,
    text: String,
    url: String,
    linkColor: Color,
) {
    val uriHandler = LocalUriHandler.current

    val formattedText = buildAnnotatedString {
        pushStringAnnotation(
            tag = "URL",
            annotation = url
        )
        withStyle(
            style = SpanStyle(
                color = linkColor,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(text)
        }
        pop()
    }
    ClickableText(
        modifier = modifier,
        text = formattedText,
        onClick = { offset ->
            formattedText.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    uriHandler.openUri(annotation.item)
                }
        }
    )
}