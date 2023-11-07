package com.luisfagundes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.luisfagundes.commons.components.R
import kotlinx.coroutines.flow.SharedFlow

/**
 * Displays a toast message when a bookmark event occurs and triggers callbacks based on the event.
 *
 * @param bookmarkEvent The shared flow of bookmark events.
 * @param onRecipeBookmarked Callback invoked when a recipe is bookmarked.
 * @param onBookmarkRemoved Callback invoked when a bookmark is removed.
 */
@Composable
fun BookmarkToastEvent(
    bookmarkEvent: SharedFlow<Boolean>,
    onRecipeBookmarked: () -> Unit = {},
    onBookmarkRemoved: () -> Unit = {},
) {
    val context = LocalContext.current
    val recipeBookmarkedMsg = stringResource(R.string.recipe_bookmarked)
    val bookmarkRemovedMsg = stringResource(R.string.bookmark_removed)

    LaunchedEffect(bookmarkEvent) {
        bookmarkEvent.collect { isBookmarked ->
            val (message, callback) = if (isBookmarked) {
                recipeBookmarkedMsg to onRecipeBookmarked
            } else {
                bookmarkRemovedMsg to onBookmarkRemoved
            }

            showToast(context, message)

            callback()
        }
    }
}
