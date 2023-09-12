package com.luisfagundes.remote.service

import com.luisfagundes.recipe.data.models.RecipeAutoCompleteResponse
import com.luisfagundes.recipe.data.models.RecipeBodyResponse
import com.luisfagundes.remote.models.RecipeResponse
import com.luisfagundes.remote.models.VideoGuideBodyResponse
import com.luisfagundes.remote.models.VideoGuideResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

private const val MAIN_COURSE_TYPE = "maincourse"

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

    @GET("food/videos/search")
    suspend fun fetchVideoGuides(
        @Query("number") number: Int = 10,
        @Query("type") query: String = MAIN_COURSE_TYPE
    ): VideoGuideBodyResponse
}
