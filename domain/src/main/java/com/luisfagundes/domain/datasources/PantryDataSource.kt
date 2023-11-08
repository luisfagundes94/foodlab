package com.luisfagundes.domain.datasources

import com.luisfagundes.domain.models.PantryCategory

interface PantryDataSource {
    suspend fun fetchPantryCategories(): List<PantryCategory>
    suspend fun fetchCommonPantryItems(): List<PantryCategory>
    suspend fun fetchPantryCategoryNames(): List<String>
}