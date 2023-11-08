package com.luisfagundes.domain.repositories

import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.framework.network.Result

interface PantryRepository {
    suspend fun fetchPantryCategories(): Result<List<PantryCategory>>
    suspend fun fetchCommonPantryItems(): Result<List<PantryCategory>>
}