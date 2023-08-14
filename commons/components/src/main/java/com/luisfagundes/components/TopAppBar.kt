package com.luisfagundes.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
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
import com.luisfagundes.resources.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodlabTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    onActionClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.verySmall),
                text = stringResource(id = titleRes),
            )
        },
        colors = colors,
        modifier = modifier.testTag("FoodlabTopAppBar"),
        actions = {
            Icon(
                modifier = Modifier
                    .clickable { onActionClick() }
                    .padding(horizontal = MaterialTheme.spacing.small),
                imageVector = actionIcon,
                contentDescription = actionIconContentDescription,
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodlabTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    onActionClick: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.verySmall),
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
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            )
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small),
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
    )
}
