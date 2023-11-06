package com.luisfagundes.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.luisfagundes.commons.components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodlabTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    navigationIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    navigationIconDescription: String = stringResource(R.string.back),
    onActionClick: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = titleRes),
                )
            },
            colors = colors,
            modifier = modifier.testTag("FoodlabTopAppBar"),
            navigationIcon = {
                IconButton(
                    onClick = onNavigationClick,
                    content = {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationIconDescription,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                )
            },
            actions = {
                actionIcon?.let {
                    IconButton(onClick = onActionClick) {
                        Icon(
                            modifier = Modifier,
                            imageVector = actionIcon,
                            contentDescription = actionIconContentDescription,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
        )
        content()
    }
}
