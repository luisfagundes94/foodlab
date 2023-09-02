package com.luisfagundes.remote.paging

import androidx.paging.PagingSource
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.remote.datasources.RemoteRecipeDataSource
import com.luisfagundes.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class RecipePagingSourceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val remoteDataSource: RemoteRecipeDataSource = mockk()
    private val defaultQueryMap = mapOf("" to "")
    private lateinit var recipePagingSource: RecipePagingSource

    @Before
    fun setUp() {
        recipePagingSource = RecipePagingSource(
            remoteDataSource,
            defaultQueryMap
        )
    }

    @Test
    fun `SHOULD return a success load result when load is called`() = runTest {
        // Arrange
        coEvery {
            remoteDataSource.fetchRecipes(any())
        } returns FakeRecipeFactory.recipeListBody

        // Act
        val result = recipePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // Assert
        val expectedData = listOf(
            FakeRecipeFactory.recipe,
            FakeRecipeFactory.recipe,
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expectedData,
                prevKey = null,
                nextKey = 21
            ),
            result
        )
    }

    @Test(expected = RuntimeException::class)
    fun `SHOULD return an error load when load is called`() = runTest {
        // Arrange
        val exception = RuntimeException()
        coEvery { remoteDataSource.fetchRecipes(any()) } throws exception

        // Act
        val result = recipePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, Recipe>(exception),
            result
        )
    }

}