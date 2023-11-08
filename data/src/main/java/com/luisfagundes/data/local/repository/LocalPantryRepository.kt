package com.luisfagundes.data.local.repository

import com.luisfagundes.domain.datasources.PantryDataSource
import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.domain.repositories.PantryRepository
import com.luisfagundes.framework.network.Result
import com.luisfagundes.framework.network.safeApiCall
import javax.inject.Inject

class LocalPantryRepository @Inject constructor(
    private val dataSource: PantryDataSource,
): PantryRepository {
    override suspend fun fetchPantryCategories() = safeApiCall {
        dataSource.fetchPantryCategories()
    }

    override suspend fun fetchCommonPantryItems() = safeApiCall {
        dataSource.fetchCommonPantryItems()
    }
}