package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.MockkUnitTest
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetRecipeDetailsTest : MockkUnitTest() {

    private val repository = mockk<RecipeRepository>(relaxed = true)
    private lateinit var getRecipeDetails: GetRecipeDetails

    override fun onCreate() {
        super.onCreate()
        getRecipeDetails = GetRecipeDetails(
            repository = repository,
            dispatcher = testCoroutineRule.testDispatcher
        )
    }

    @Test
    fun verifyExecution() = runTest {
        // Given
        val recipeId = 1
        val params = GetRecipeDetails.Params(recipeId)

        // When
        getRecipeDetails.invoke(params).toList()

        // Then
        coVerify { repository.getRecipeDetails(recipeId) }
    }
}
