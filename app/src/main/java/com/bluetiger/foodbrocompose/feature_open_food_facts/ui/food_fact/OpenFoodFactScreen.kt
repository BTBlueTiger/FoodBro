package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact

import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote.dto.OpenFoodFactsResponse
import com.bluetiger.foodbrocompose.ui.common.components.async_image.AsyncImage
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodFactScreen(
    barcode: String,
    viewModel: OpenFoodFactViewModel = hiltViewModel()
) {

    val productGeneralState = viewModel.productGeneral.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    if (productGeneralState != null)
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            )
        ) {
            Row {
                HeadLine(headline = productGeneralState.brands)
            }
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .clip(shape = RoundedCornerShape(size = 12.dp)),
                url = productGeneralState.imageUrl,
                contentScale = ContentScale.Crop
            )
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetPeekHeight = 28.dp,
                sheetContent = {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(28.dp),
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
                        Spacer(Modifier.height(20.dp))
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
                    Text("Scaffold Content")
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

