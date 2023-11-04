package com.luisfagundes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.luisfagundes.data.local.models.IngredientEntity
import com.luisfagundes.data.local.models.InstructionEntity
import com.luisfagundes.data.local.models.MeasureEntity
import com.luisfagundes.data.local.models.MeasuresEntity
import com.luisfagundes.data.local.models.RecipeEntity
import com.luisfagundes.data.local.models.StepEntity
import com.luisfagundes.data.local.typeConverters.DishTypeConverter
import com.luisfagundes.data.local.typeConverters.IngredientTypeConverter
import com.luisfagundes.data.local.typeConverters.InstructionTypeConverter
import com.luisfagundes.data.local.typeConverters.MeasureTypeConverter
import com.luisfagundes.data.local.typeConverters.MeasuresTypeConverter
import com.luisfagundes.data.local.typeConverters.StepTypeConverter

@Database(
    entities = [
        RecipeEntity::class,
        IngredientEntity::class,
        InstructionEntity::class,
        StepEntity::class,
        MeasuresEntity::class,
        MeasureEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DishTypeConverter::class,
    InstructionTypeConverter::class,
    IngredientTypeConverter::class,
    MeasuresTypeConverter::class,
    MeasureTypeConverter::class,
    StepTypeConverter::class,
)
abstract class FoodlabDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}