package com.bluetiger.foodbrocompose.user

import com.bluetiger.foodbrocompose.ui.theme.FoodBroModifierType

enum class PersonDataValueType(
    val label: String,
    val placeHolder: String,
    val modifier: FoodBroModifierType
) {
    NAME("Name", "example@aol.com", FoodBroModifierType.SIMPLE_OUTLINED_TEXT),
    WEIGHT("Weight", "80 kg", FoodBroModifierType.SIMPLE_OUTLINED_TEXT),
    HEIGHT("Height", "1.80 cm", FoodBroModifierType.SIMPLE_OUTLINED_TEXT),
    DATE("Birthday", "28.08.1995", FoodBroModifierType.SIMPLE_OUTLINED_TEXT),
    GENDER("Gender", "None", FoodBroModifierType.SIMPLE_OUTLINED_TEXT)
}