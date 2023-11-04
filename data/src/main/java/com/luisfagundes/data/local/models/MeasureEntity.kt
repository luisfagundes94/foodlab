package com.luisfagundes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measure")
data class MeasureEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "unit_long") val unitLong: String,
    @ColumnInfo(name = "unit_short") val unitShort: String
)
