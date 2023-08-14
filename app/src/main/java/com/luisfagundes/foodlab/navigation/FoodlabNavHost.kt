package com.luisfagundes.foodlab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.saved.navigation.savedScreen
import com.luisfagundes.foodlab.ui.FoodlabAppState
import com.luisfagundes.home.navigation.homeNavigationRoute
import com.luisfagundes.home.navigation.homeGraph
import com.luisfagundes.feature.pantry.navigation.pantryScreen
import com.luisfagundes.home.navigation.homeNavigationGraph
import com.luisfagundes.recipes.details.navigateToRecipeDetails
import com.luisfagundes.recipes.details.recipeDetailsScreen
import com.luisfagundes.search.navigation.searchScreen

@Composable
fun FoodlabNavHost(
    appState: FoodlabAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationGraph,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            onRecipeClick = navController::navigateToRecipeDetails,
            nestedGraphs = {
                recipeDetailsScreen(
                    onBackClick = navController::popBackStack,
                )
            }
        )
        searchScreen(onRecipeClick = {})
        savedScreen(onRecipeClick = {})
        pantryScreen()
    }
}
