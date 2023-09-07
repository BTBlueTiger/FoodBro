package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.components.drop_down.bottem_sheets.OpenFoodFactsDropDown
import com.bluetiger.foodbrocompose.ui.common.components.async_image.AsyncImage
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenFoodFactScreen(
    viewModel: OpenFoodFactViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()


    if (viewModel.openFoodFactsData.value.status == 0) {
        Log.e("OpenFoodFactsBarcodeState", "NULL")
    } else {

        val bottomSheet = viewModel.bottomSheet.value
        val brands = viewModel.getBrands()
        val imageByteArray = viewModel.getImageUrl()
        var expanded by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 22.dp,
            )
        ) {
            Row {
                HeadLine(headline = brands ?: "No Brands")
            }
            Divider(Modifier.height(10.dp))

            if (imageByteArray != null) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .clip(shape = RoundedCornerShape(size = 12.dp)),
                        byteArray = imageByteArray,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetPeekHeight = 64.dp,
                sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                        title = { HeadLine(headline = brands ?: "No Brand") },
                        actions = {
                            IconButton(
                                onClick = { expanded = !expanded }
                            ) {
                                Icon(
                                    imageVector = Icons.TwoTone.Menu,
                                    contentDescription = ""
                                )
                            }
                            OpenFoodFactsDropDown(
                                expanded = expanded,
                                onDismiss = { expanded = false }

                            ) {
                                viewModel.onEvent(OpenFoodFactEvents.ChangeBottomSheet(it))
                            }
                        })
                },
                sheetContent = {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .height(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Swipe up to expand sheet")
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()

                            .padding(64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Sheet content")
                        Spacer(Modifier.height(100.dp))
                        Button(
                            onClick = {
                                scope.launch { scaffoldState.bottomSheetState.partialExpand() }
                            }
                        ) {
                            Text("Click to collapse sheet")
                        }
                    }
                }) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    bottomSheet.Sheet()
                }
            }

        }
    }
}

@Composable
fun FourButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = "Button 1")
        }
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = "Button 2")
        }
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = "Button 3")
        }
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = "Button 4")
        }
    }
}

