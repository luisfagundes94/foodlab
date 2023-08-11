package com.luisfagundes.foodlab.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarDuration.Short
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.luisfagundes.components.FoodlabBackground
import com.luisfagundes.components.FoodlabNavigationBar
import com.luisfagundes.components.FoodlabNavigationBarItem
import com.luisfagundes.components.FoodlabTopAppBar
import com.luisfagundes.foodlab.app.R
import com.luisfagundes.foodlab.navigation.FoodlabNavHost
import com.luisfagundes.foodlab.navigation.TopLevelDestination
import com.luisfagundes.remote.utils.NetworkMonitor

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun FoodlabApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    appState: FoodlabAppState = rememberFoodlabAppState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass,
    ),
) {
    FoodlabBackground(
        color = MaterialTheme.colorScheme.background
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val isOffline by appState.isOffline.collectAsStateWithLifecycle()
        val notConnectedMessage = stringResource(R.string.not_connected)

        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = Indefinite,
                )
            }
        }
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    FoodlabBottomBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier.testTag("FoodlabBottomBar"),
                    )
                }
            },
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                Column(Modifier.fillMaxSize()) {
                    val destination = appState.currentTopLevelDestination
                    if (destination != null) {
                        FoodlabTopAppBar(
                            modifier = Modifier,
                            titleRes = destination.titleTextId,
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.Transparent,
                            )
                        )
                    }
                    FoodlabNavHost(appState = appState, onShowSnackbar = { message, action ->
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = action,
                            duration = Short,
                        ) == ActionPerformed
                    })
                }
            }
        }
    }
}

@Composable
private fun FoodlabBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    FoodlabNavigationBar(
        modifier = modifier,
        content = {
            destinations.forEach { destination ->
                val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
                FoodlabNavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) },
                    modifier = Modifier,
                )
            }
        }
    )
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
