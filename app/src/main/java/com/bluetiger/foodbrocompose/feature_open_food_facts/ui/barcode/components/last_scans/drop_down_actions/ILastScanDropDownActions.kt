package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions

import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData

sealed interface ILastScanDropDownActions {

    val name: String
    val iconId: Int
    val isClickable: Boolean
    operator fun invoke(argument: OpenFoodFactsData)

}
/*
    object Information : LastScanDropDownActions() {
        override val name: String = "Detailed Information"
        override val iconId: Int = 1
        override fun onClick() {
            TODO("Not yet implemented")
        }
    }

    object AddToPersonalList: LastScanDropDownActions() {
        override val name: String = "Detailed Information"
        override val iconId: Int = 1
        override fun onClick() {
            TODO("Not yet implemented")
        }
    }

    object RemoveFromPersonalList : LastScanDropDownActions() {
        override val name: String = "Detailed Information"
        override val iconId: Int = 1
        override fun onClick() {
            TODO("Not yet implemented")
        }
    }

    object AddToFamilyList : LastScanDropDownActions() {
        override val name: String = "Detailed Information"
        override val iconId: Int = 1
        override fun onClick() {
            TODO("Not yet implemented")
        }
    }

    object AddToFavorites: LastScanDropDownActions() {
        override val name: String = "Detailed Information"
        override val iconId: Int = 1
        override fun onClick() {
            TODO("Not yet implemented")
        }
    }

    object RemoveFromFavorites: LastScanDropDownActions() {
        override val name: String = "Remove From Favorites"
        override val iconId: Int = 1
        override fun onClick() {
            TODO("Not yet implemented")
        }
    }


}


 */