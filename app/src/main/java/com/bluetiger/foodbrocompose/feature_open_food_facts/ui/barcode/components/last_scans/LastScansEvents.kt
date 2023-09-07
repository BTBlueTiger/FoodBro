package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions.ILastScanDropDownActions

sealed class LastScansEvents {
    data class onDropDownActionClicked(
        val action: ILastScanDropDownActions,
        val openFoodFactsData: OpenFoodFactsData
    ) : LastScansEvents()
}
