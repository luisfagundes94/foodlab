package com.luisfagundes.foodlab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.saved.navigation.savedGraph
import com.luisfagundes.foodlab.ui.FoodlabAppState
import com.luisfagundes.home.navigation.homeGraph
import com.luisfagundes.feature.pantry.navigation.pantryGraph
import com.luisfagundes.home.navigation.homeNavigationGraph
import com.luisfagundes.recipes.details.navigation.navigateToRecipeDetails
import com.luisfagundes.recipes.details.navigation.recipeDetailsScreen
import com.luisfagundes.search.navigation.searchGraph

@Composable
fun FoodlabNavHost(
    appState: FoodlabAppState,
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
        searchGraph(
            onRecipeClick = {}
        )
        savedGraph(
            onRecipeClick = navController::navigateToRecipeDetails,
            nestedGraphs = {
                recipeDetailsScreen(
                    onBackClick = navController::popBackStack,
                )
            }
        )
        pantryGraph()
    }
}
