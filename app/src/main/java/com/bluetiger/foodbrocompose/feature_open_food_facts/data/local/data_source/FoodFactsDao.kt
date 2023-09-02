package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source

import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsResponseData

interface FoodFactsDao : BaseDao<OpenFoodFactsResponseData> {

    @Query("SELECT * FROM OpenFoodFactsResponses WHERE barcode = :barcode")
    suspend fun getOpenFoodFactsByBarcode(barcode: String) : OpenFoodFactsResponseData


}