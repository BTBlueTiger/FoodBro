package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.bottem_sheets.OpenFoodFactGeneralSheet
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.bottem_sheets.OpenFoodFactNutriScoreSheet
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.bottem_sheets.OpenFoodFactNutrientLevelSheet

sealed class OpenFoodFactSheet {
    
    abstract val headline : String
    abstract val leadingIcon : Int
    @Composable
    abstract fun Sheet()

    object General : OpenFoodFactSheet() {
        override val headline: String
            get() = "General Information"
        override val leadingIcon: Int
            get() = R.drawable.twotone_info_24

        @Composable
        override fun Sheet() = OpenFoodFactGeneralSheet()
    }

    object NutrientLevel : OpenFoodFactSheet() {
        override val headline: String
            get() = "Nutrient Level"
        override val leadingIcon: Int
            get() = R.drawable.baseline_align_vertical_bottom_24

        @Composable
        override fun Sheet() = OpenFoodFactNutrientLevelSheet()
    }

    object NutriScore : OpenFoodFactSheet() {
        override val headline: String
            get() = "NutriScore"
        override val leadingIcon: Int
            get() = R.drawable.baseline_score_24

        @Composable
        override fun Sheet() = OpenFoodFactNutriScoreSheet()
    }
}