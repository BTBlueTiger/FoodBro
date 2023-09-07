package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData

data class LastScanDropDownActions (
    val informationAction: InformationAction,
    val addToFavorites: AddToFavorites,
    val removeFromFavorites: RemoveFromFavorites,
    val addToList: AddToList,
    val removeFromList: RemoveFromList
){
    operator fun iterator() : Iterator<ILastScanDropDownActions> {
        return listOf(
            informationAction, addToFavorites, removeFromFavorites, addToList, removeFromList
        ).filter { it.isClickable }.iterator()
    }
}