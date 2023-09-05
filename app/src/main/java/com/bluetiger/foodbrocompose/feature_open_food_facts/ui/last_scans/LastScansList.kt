package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.last_scans

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.database.FBPreferences

@Composable
fun LastScansList(
    viewModel: LastScansViewModel = hiltViewModel(),
    navigateToOpenFoodFacts: () -> Unit
) {
    val foodFacts = viewModel.scannedFoodFacts.collectAsState(initial = emptyList())

    foodFacts.value.forEach {
        Row {
            Button(onClick = {
                FBPreferences.getInstance().setDesiredOpenFoodFactsData(it.timeStamp)
                navigateToOpenFoodFacts()
            }) {
                Text(text = it.productGeneral?.brands.toString())
            }
        }
    }

}