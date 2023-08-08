package com.bluetiger.foodbrocompose.ui.theme

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FoodBroModifierType {
    SIMPLE_DEFAULT_BOX,
    SIMPLE_OUTLINED_TEXT,
    SIMPLE_DIVIDER,
    SIMPLE_HEADLINE
}

@Composable
fun foodBroModifier(modifier: FoodBroModifierType) =
    when(modifier){
        FoodBroModifierType.SIMPLE_DEFAULT_BOX ->
            Modifier.padding(20.dp)
        FoodBroModifierType.SIMPLE_OUTLINED_TEXT ->
            Modifier.padding(
                start = 10.dp,
                end = 10.dp
            )
        FoodBroModifierType.SIMPLE_DIVIDER ->
            Modifier.padding(
                top = 10.dp, bottom = 10.dp
            )
        FoodBroModifierType.SIMPLE_HEADLINE ->
            Modifier
                .padding(5.dp)
    }