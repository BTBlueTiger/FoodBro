package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels

import androidx.room.TypeConverter
import com.bluetiger.foodbrocompose.database.room.DataConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class NutrientLevelsDataConverter : DataConverter<NutrientLevels> {
    @TypeConverter
    override fun fromData(data: NutrientLevels): String = Json.encodeToString(data)
    @TypeConverter
    override fun toData(json: String): NutrientLevels = Json.decodeFromString(json)
}