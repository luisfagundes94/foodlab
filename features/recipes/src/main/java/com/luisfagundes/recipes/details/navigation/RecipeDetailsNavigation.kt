package com.luisfagundes.recipes.details.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.recipes.details.presentation.RecipeDetailsRoute
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber

@VisibleForTesting
internal const val recipeIdArg = "recipeId"
internal const val recipeDetailsRoute = "recipeDetails/"

internal class RecipeDetailsArg(val recipeId: String) {
    constructor(
        savedStateHandle: SavedStateHandle,
        stringDecoder: StringDecoder
    ) : this(stringDecoder.decode(checkNotNull(savedStateHandle[recipeIdArg])))
}

fun NavController.navigateToRecipeDetails(recipeId: String) {
    val encodedId = Uri.encode(recipeId)
    this.navigate("$recipeDetailsRoute$encodedId") {
        launchSingleTop = true
    }
    Timber.d("RecipeDetailsRoute: ${this.currentDestination?.route}")
}

fun NavGraphBuilder.recipeDetailsScreen(
    onBackClick: () -> Unit,
) {
    composable(
        route = "$recipeDetailsRoute{$recipeIdArg}",
        arguments = listOf(
            navArgument(recipeIdArg) { type = NavType.StringType },
        ),
    ) {
        RecipeDetailsRoute(
            onBackClick = onBackClick,
        )
    }
}