package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs

import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.AddEditUserActivityTabRow
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings.AddEditUserNutritionSettingsContent
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.personal.AddEditUserPersonalTabRow

sealed class AddEditUserTab() {

    abstract val titleName: String
    abstract val iconId : Int
    @Composable
    abstract fun TabRow()

    object PersonalInformation : AddEditUserTab(){
        override val titleName: String
            get() = "Personal"
        override val iconId: Int
            get() = R.drawable.twotone_person_24
        @Composable
        override fun TabRow() = AddEditUserPersonalTabRow()
    }
    object ActivityInformation : AddEditUserTab(){
        override val titleName: String
            get() = "Activity"

        override val iconId: Int
            get() = R.drawable.twotone_sports_24
        @Composable
        override fun TabRow() = AddEditUserActivityTabRow()
    }

    object FoodNutritionSettings : AddEditUserTab(){
        override val titleName: String
            get() = "Nutrition Settings"
        override val iconId: Int
            get() = R.drawable.twotone_settings_24

        @Composable
        override fun TabRow() = AddEditUserNutritionSettingsContent()
    }
}
