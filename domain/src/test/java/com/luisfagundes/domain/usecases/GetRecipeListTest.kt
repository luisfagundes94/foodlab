package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.MockkUnitTest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetRecipeListTest : MockkUnitTest() {

    private val repository = mockk<RecipeRepository>(relaxed = true)
    private lateinit var getRecipeList: GetRecipeList

    override fun onCreate() {
        super.onCreate()
        getRecipeList = GetRecipeList(
            repository,
            testCoroutineRule.testDispatcher
        )
    }

    @Test
    fun verifyExecution() = runTest {
        // Given
        val params = GetRecipeList.Params("desc")

        // When
        getRecipeList.invoke(params)

        // Then
        coVerify { repository.getRecipeList(params) }
    }
}