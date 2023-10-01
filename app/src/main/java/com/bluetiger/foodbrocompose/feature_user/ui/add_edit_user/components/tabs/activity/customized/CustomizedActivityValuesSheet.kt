package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.customized

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.ActivitySettingsType
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.AddEditUserActivityEvent
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.AddEditUserActivityViewModel
import kotlin.math.floor

@Composable
private fun ActivitySliderCard(
    activity: UserActivityInformation.ValueType,
    value: Float,
    onValueChangedFinished : (Float) -> Unit
) {
    var mutableValue by remember { mutableFloatStateOf(value) }
    var mutableHours by remember { mutableIntStateOf(0) }
    var mutableMinutes by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = mutableValue) {
        mutableHours = if (mutableValue > 0) {
            floor(mutableValue / 60).toInt()
        } else {
            0
        }
        mutableMinutes = floor(mutableValue % 60).toInt()
    }

    LaunchedEffect(key1 = mutableHours, key2 = mutableHours) {
        mutableValue = 60f * mutableHours + mutableMinutes

    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = activity.shortTerm, modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                OutlinedTextField(
                    value = mutableHours.toString(),
                    onValueChange = {
                        mutableHours = if (it.toIntOrNull() != null && it.toInt() < 24)
                            it.toInt()
                        else {
                            23
                        }
                    },
                    label = { Text(text = "Hours") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = String.format("%02d", mutableMinutes),
                    onValueChange = {
                        mutableHours = if (it.toIntOrNull() != null && it.toInt() < 60)
                            it.toInt()
                        else {
                            59
                        }
                    },
                    label = { Text(text = "Minutes") },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Slider(
            value = mutableValue,
            valueRange = 0f..24f * 60,
            onValueChange = { mutableValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        mutableValue += dragAmount.x / /* your sensitivity factor */ 10
                    }
                },
            steps = 24,
            onValueChangeFinished = { onValueChangedFinished(mutableValue) }
        )
    }
}

@Composable
fun CustomizedActivityValuesSheet(
    viewModel: AddEditUserActivityViewModel = hiltViewModel()
) {
    val items = UserActivityInformation.ValueType.values().toList()
    Column {
        LazyVerticalGrid(GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(items) { activity ->
                ActivitySliderCard(
                    activity = activity,
                    value = viewModel.activityInformation[activity]!!
                ) {
                    viewModel.onEvent(
                        AddEditUserActivityEvent.CustomActivityValueChanged(
                            activity,
                            it
                        )
                    )
                }
            }
        }
    }
}