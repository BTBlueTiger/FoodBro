package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.bottem_sheets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OpenFoodFactsDropDown(expanded: Boolean, onDismiss: () -> Unit, onClick: (OpenFoodFactSheet) -> Unit) {

    var selectedIndex by remember { mutableStateOf(0) }


    val menuItems = listOf(
        OpenFoodFactSheet.General,
        OpenFoodFactSheet.NutriScore,
        OpenFoodFactSheet.NutrientLevel
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.padding(end = 16.dp)
    ) {
        menuItems.forEachIndexed { index, it ->
            DropdownMenuItem(
                text = { Text(text = it.headline) },
                leadingIcon = {
                    Icon(
                        painterResource(id = it.leadingIcon),
                        contentDescription = ""
                    )
                },
                onClick = {
                    selectedIndex = index
                    onClick(it)
                    onDismiss()
                }
            )
        }
    }
}