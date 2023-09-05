package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case

data class OpenFoodFactDataUseCases(
    val getLastOpenFoodFactData: GetLastOpenFoodFactData,
    val getOpenFoodFactByTimeStamp: GetOpenFoodFactByTimeStamp,
    val getOpenFoodFactsByUser: GetOpenFoodFactsByUser,
    val insertOpenFoodFact: InsertOpenFoodFact,
    val setLastOpenFoodFactData: SetLastOpenFoodFactData
)