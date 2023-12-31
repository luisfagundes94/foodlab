package com.luisfagundes.foodlab.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.luisfagundes.data.remote.utils.NetworkMonitor
import com.luisfagundes.saved.navigation.savedNavigationRoute
import com.luisfagundes.saved.navigation.navigateToSaved
import com.luisfagundes.foodlab.navigation.TopLevelDestination
import com.luisfagundes.home.navigation.homeNavigationRoute
import com.luisfagundes.home.navigation.navigateToHome
import com.luisfagundes.pantry.navigation.navigateToPantry
import com.luisfagundes.pantry.navigation.pantryNavigationRoute
import com.luisfagundes.search.navigation.navigateToSearch
import com.luisfagundes.search.navigation.searchNavigationRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class FoodlabAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    private val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> TopLevelDestination.HOME
            searchNavigationRoute -> TopLevelDestination.SEARCH
            savedNavigationRoute -> TopLevelDestination.SAVED
            pantryNavigationRoute -> TopLevelDestination.PANTRY
            else -> null
        }

    val shouldShowBottomBar: Boolean
        @Composable get() = currentTopLevelDestination != null

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.SEARCH -> navController.navigateToSearch(topLevelNavOptions)
                TopLevelDestination.SAVED -> navController.navigateToSaved(topLevelNavOptions)
                TopLevelDestination.PANTRY -> navController.navigateToPantry(topLevelNavOptions)
            }
        }
    }
}

@Composable
fun rememberFoodlabAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): FoodlabAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
    ) {
        FoodlabAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor,
        )
    }
}
