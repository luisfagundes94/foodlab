package com.luisfagundes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.luisfagundes.common.components.R
import com.luisfagundes.resources.theme.spacing

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.generic_error_message),
    onRetryClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(MaterialTheme.spacing.default))
        Button(onClick = onRetryClick) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}