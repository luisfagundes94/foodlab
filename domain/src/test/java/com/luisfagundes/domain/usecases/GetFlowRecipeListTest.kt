package com.luisfagundes.domain.usecases

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.pagingsource.RecipePagingSource
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.MockkUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetFlowRecipeListTest : MockkUnitTest() {

    @MockK(relaxed = true)
    lateinit var repository: RecipeRepository

    @SpyK
    @InjectMockKs
    private lateinit var getFlowRecipeList: GetFlowRecipeList

    @Test
    fun verifyExecution() = runTest {
        // Given
        val pagingConfig = PagingConfig(pageSize = 20)
        val params = GetFlowRecipeList.Params(pagingConfig, "desc")

        // When
        getFlowRecipeList.invoke(params)

        // Then
        coVerify { getFlowRecipeList.invoke(any()) }
    }
}