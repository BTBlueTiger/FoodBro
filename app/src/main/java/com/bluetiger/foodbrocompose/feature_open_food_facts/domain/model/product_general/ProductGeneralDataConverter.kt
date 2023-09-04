package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general

import androidx.room.TypeConverter
import com.bluetiger.foodbrocompose.database.room.DataConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class ProductGeneralDataConverter : DataConverter<ProductGeneral> {
    @TypeConverter
    override fun fromData(data: ProductGeneral): String = Json.encodeToString(data)

    @TypeConverter
    override fun toData(json: String): ProductGeneral = Json.decodeFromString(json)
}