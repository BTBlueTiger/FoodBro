package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.drop_down_actions.InformationAction
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.bottem_sheets.OpenFoodFactsDropDown
import com.bluetiger.foodbrocompose.ui.common.components.async_image.AsyncImage

@Composable
fun LastScansList(
    navigateToOpenFoodFact: () -> Unit,
    viewModel: LastScansViewModel = hiltViewModel()
) {
    val foodFacts =
        viewModel.scannedFoodFacts?.collectAsState(initial = emptyList())?.value ?: emptyList()
    val dropDownActions = viewModel.lastScanDropDownActions

    LazyColumn {
        items(foodFacts) { scannedFoodFact ->
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                scannedFoodFact.productGeneral?.imageByteArray?.let {
                    AsyncImage(
                        modifier = Modifier.size(100.dp), // Adjust the size as needed
                        byteArray = it,
                        contentScale = ContentScale.Crop
                    )
                }
                scannedFoodFact.productGeneral?.brands?.let { Text(text = it) }
                var expended by remember {
                    mutableStateOf(false)
                }
                IconButton(onClick = { expended = true }) {
                    Icon(imageVector = Icons.TwoTone.Menu, contentDescription = "")
                    DropdownMenu(
                        expanded = expended,
                        onDismissRequest = { expended = false }) {
                        dropDownActions.iterator().forEach {
                            DropdownMenuItem(
                                text = { Text(text = it.name) },
                                leadingIcon = {
                                    Icon(
                                        painterResource(id = it.iconId),
                                        contentDescription = ""
                                    )
                                },
                                onClick = {
                                    it(scannedFoodFact)

                                    when (it) {
                                        is InformationAction -> {
                                            navigateToOpenFoodFact()
                                        }
                                        else -> {

                                        }
                                    }
                                },
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}