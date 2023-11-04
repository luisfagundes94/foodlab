package com.luisfagundes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
    @ColumnInfo(name = "measures") val measures: String,
    @ColumnInfo(name = "original_measure") val originalMeasure: String
)

