package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions

import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData

class RemoveFromFavorites : ILastScanDropDownActions {
    override val name: String = "Remove from Favorites"
    override val iconId: Int = R.drawable.twotone_star_border_24
    override val isClickable: Boolean = false
    override operator fun invoke(argument: OpenFoodFactsData) {

    }
}