package com.luisfagundes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luisfagundes.domain.models.Step

@Entity(tableName = "instructions")
data class InstructionEntity(
    @PrimaryKey(autoGenerate = true) val instructionId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "steps") val steps: List<StepEntity>,
)

