package com.luisfagundes.foodlab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.feature.favorites.navigation.favoritesScreen
import com.luisfagundes.foodlab.ui.FoodlabAppState
import com.luisfagundes.home.navigation.homeNavigationRoute
import com.luisfagundes.home.navigation.homeScreen
import com.luisfagundes.feature.pantry.navigation.pantryScreen
import com.luisfagundes.search.navigation.searchScreen

@Composable
fun FoodlabNavHost(
    appState: FoodlabAppState,
    onShowSnackbar: suspend(String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onRecipeClick = {})
        searchScreen(onRecipeClick = {})
        favoritesScreen(onRecipeClick = {})
        pantryScreen()
    }
}
