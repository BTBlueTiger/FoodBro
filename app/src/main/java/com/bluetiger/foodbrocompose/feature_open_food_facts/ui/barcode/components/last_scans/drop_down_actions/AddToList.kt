package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions

import androidx.navigation.NamedNavArgument
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData

class AddToList : ILastScanDropDownActions {
    override val name: String = "Add to list"
    override val iconId: Int = R.drawable.twotone_list_add_24
    override val isClickable: Boolean = true
    override operator fun invoke(argument: OpenFoodFactsData) {

    }
}