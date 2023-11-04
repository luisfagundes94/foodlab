package com.luisfagundes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class StepEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "step") val step: String
)

