package com.luisfagundes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisfagundes.domain.models.Measure

@Entity(tableName = "measures")
data class MeasuresEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "metric_measure") val metricMeasure: Measure, // JSON string
    @ColumnInfo(name = "us_measure") val usMeasure: Measure // JSON string
)
