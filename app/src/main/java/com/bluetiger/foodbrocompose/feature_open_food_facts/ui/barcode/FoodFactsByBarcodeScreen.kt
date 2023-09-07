package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.barcode_scanner.BarcodeScannerVideoView
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.barcode.components.last_scans.LastScansList
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextField
import kotlinx.coroutines.launch

@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
fun FoodFactsByBarcodeScreen(
    viewModel: FoodFactsByBarcodeViewModel = hiltViewModel(),
    navigateToOpenFoodFacts: () -> Unit
) {

    val context = LocalContext.current

    val camaraScannerShowState = viewModel.camaraScannerShow.value
    val floatingButtonImageState = viewModel.floatingButtonImage.value
    val barcodeState = viewModel.barcode.value
    val flashModeState = viewModel.flashMode.value
    val responseState = viewModel.response.value

    val barcodeScanner = BarcodeScannerVideoView()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()



    LaunchedEffect(key1 = responseState) {
        if (responseState.barcode.isNotEmpty())
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "A Product from: ${responseState.productGeneral?.brands} is found",
                    actionLabel = "Show",
                    withDismissAction = true
                ).also {
                    if (it == SnackbarResult.ActionPerformed) {
                        FBPreferences.getInstance()
                            .setDesiredOpenFoodFactsData(-1)
                        navigateToOpenFoodFacts()
                    }
                }
            }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.event(
                    FoodFactsByBarcodeEvents.CamaraShowEvent(
                        context,
                        floatingButtonImageState
                    )
                )
            }) {
                Icon(
                    painterResource(id = floatingButtonImageState),
                    contentDescription = "Scan a Barcode"
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if (barcodeState.isValid) {
                    Row(Modifier, horizontalArrangement = Arrangement.Center) {
                        Button(onClick = {
                            viewModel.event(FoodFactsByBarcodeEvents.BarcodeResponseRequest)
                        })
                        {
                            Text(text = "Send Request")
                        }
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp), Arrangement.SpaceEvenly
                ) {
                    ConditionOutlineTextField(
                        state = barcodeState,
                        onValueChange = {
                            viewModel.event(
                                FoodFactsByBarcodeEvents.BarcodeEnteredEvent(
                                    it
                                )
                            )
                        },
                        label = { Text(text = "Barcode") },
                        placeholder = { Text(text = "Type a barcode or scan it") })
                }
                if (camaraScannerShowState) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.4f)
                                .clipToBounds()
                        ) {
                            barcodeScanner.VideoCapture(
                                modifier = Modifier.matchParentSize(),
                                onBarcodeFound = {
                                    viewModel.event(FoodFactsByBarcodeEvents.BarcodeFound(it))
                                }
                            )
                        }
                    }
                } else {
                    LastScansList({ navigateToOpenFoodFacts() })
                }
            }
        }
    }
}
