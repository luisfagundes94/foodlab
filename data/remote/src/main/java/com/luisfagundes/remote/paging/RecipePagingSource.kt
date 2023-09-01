package com.luisfagundes.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.pagingsource.RecipePagingSource
import retrofit2.HttpException
import java.io.IOException

class RecipePagingSource(
    private val recipeDataSource: RecipeDataSource,
    private val queryMap: Map<String, String>
) : PagingSource<Int, Recipe>(), RecipePagingSource {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            getLoadResult(params)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun getLoadResult(params: LoadParams<Int>): LoadResult.Page<Int, Recipe> {
        val offset = params.key ?: DEFAULT_OFFSET
        val queries = hashMapOf(OFFSET_KEY to offset.toString())

        queries.putAll(queryMap)

        val response = recipeDataSource.fetchRecipes(queries)
        val responseOffset = response.offset
        val totalRecipes = response.totalResults

        return LoadResult.Page(
            data = response.results,
            prevKey = null,
            nextKey = calculateNextKey(responseOffset, totalRecipes)
        )
    }

    override fun calculateNextKey(responseOffset: Int, totalRecipes: Int) =
        if (responseOffset < totalRecipes) responseOffset + RESULTS_PER_PAGE
        else null

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(RESULTS_PER_PAGE) ?: anchorPage?.nextKey?.minus(
                RESULTS_PER_PAGE
            )
        }
    }

    private companion object {
        const val DEFAULT_OFFSET = 0
        const val OFFSET_KEY = "offset"
        const val RESULTS_PER_PAGE = 20
    }
}
