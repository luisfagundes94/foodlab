package com.luisfagundes.domain.pagingsource

interface RecipePagingSource {
    fun calculateNextKey(responseOffset: Int, totalRecipes: Int): Int?
}