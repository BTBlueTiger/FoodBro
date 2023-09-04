package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data

import androidx.room.TypeConverter
import com.bluetiger.foodbrocompose.database.room.DataConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
class NutriScoreDataConverter : DataConverter<NutriScoreData> {

    @TypeConverter
    override fun fromData(data: NutriScoreData): String = Json.encodeToString(data)

    @TypeConverter
    override fun toData(json: String): NutriScoreData = Json.decodeFromString(json)
}