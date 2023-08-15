package com.luisfagundes.remote.service

import com.luisfagundes.recipe.data.models.RecipeAutoCompleteResponse
import com.luisfagundes.recipe.data.models.RecipeBodyResponse
import com.luisfagundes.remote.models.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipeService {
    @GET("recipes/complexSearch")
    suspend fun fetchRecipes(
        @QueryMap
        queries: Map<String, String>,
        @Query("instructionsRequired") instructionsRequired: Boolean = true
    ): RecipeBodyResponse

    @GET("recipes/{id}/information")
    suspend fun fetchRecipeDetails(
        @Path("id") id: Int
    ): RecipeResponse

    @GET("recipes/autocomplete")
    suspend fun fetchRecipesAutoComplete(
        @Query("number") number: Int = 5,
        @Query("query") query: String
    ): List<RecipeAutoCompleteResponse>
}
