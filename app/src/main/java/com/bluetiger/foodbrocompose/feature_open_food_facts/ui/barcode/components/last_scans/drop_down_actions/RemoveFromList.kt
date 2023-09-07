package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions

import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData

class RemoveFromList : ILastScanDropDownActions {
    override val name: String = "Remove from List"
    override val iconId: Int = R.drawable.twotone_list_remove_24
    override val isClickable: Boolean = true
    override operator fun invoke(argument: OpenFoodFactsData) {

    }
}