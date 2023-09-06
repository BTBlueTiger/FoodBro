package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.screen

import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.OpenFoodFactSheet

sealed class OpenFoodFactEvents{
    
    data class ChangeBottomSheet(val sheet: OpenFoodFactSheet) : OpenFoodFactEvents()
    
}
