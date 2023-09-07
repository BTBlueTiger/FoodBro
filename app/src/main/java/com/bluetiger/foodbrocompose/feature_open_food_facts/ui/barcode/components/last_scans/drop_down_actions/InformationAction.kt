package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions

import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData

class InformationAction : ILastScanDropDownActions {
    override val name: String = "Detailed Information"
    override val iconId: Int = R.drawable.twotone_info_24
    override val isClickable: Boolean = true
    override operator fun invoke(argument: OpenFoodFactsData) {
         FBPreferences.getInstance().setDesiredOpenFoodFactsData(argument.timeStamp)
    }
}