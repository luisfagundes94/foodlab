package com.luisfagundes.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luisfagundes.data.remote.utils.getJsonDataFromAsset
import com.luisfagundes.domain.datasources.PantryDataSource
import com.luisfagundes.domain.models.PantryCategory
import javax.inject.Inject

private const val PANTRY_JSON = "pantry_items.json"

class PantryDataSourceImpl @Inject constructor(
    private val appContext: Context
): PantryDataSource {
    override suspend fun fetchPantryCategories(): List<PantryCategory> {
        val jsonFile = getJsonDataFromAsset(appContext, PANTRY_JSON)
        val gson = Gson()
        val listType = object : TypeToken<List<PantryCategory>>() {}.type
        return gson.fromJson(jsonFile, listType)
    }
}