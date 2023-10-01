package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.nutrition_settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserNutritionSettingInformation

@Composable
private fun MacroSlider(
    type: UserNutritionSettingInformation.ValueType,
    value: Int,
    difference: Float,
    onValueChange: (Float) -> Unit,
    sliderColors: SliderColors
) {

    var localValue by remember {
        mutableFloatStateOf(value.toFloat())
    }

    Spacer(modifier = Modifier.height(10.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = type.label, modifier = Modifier.weight(1f))

                Text(text = "$value %")
            }
            Row {
                Slider(
                    value = localValue,
                    valueRange = 0f..100f,
                    onValueChange = { localValue = it },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(vertical = 8.dp),
                    colors = sliderColors,
                    onValueChangeFinished = { onValueChange(localValue) }
                )
                Image(
                    painterResource(id = type.symbolIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(50.dp)
                        .clickable {
                            localValue += difference
                            onValueChange( localValue )
                        })
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditUserNutritionSettingsContent(
    viewModel: AddEditUserNutritionSettingsViewModel = hiltViewModel()
) {
    var dropDownMenuState by remember { mutableStateOf(false) }

    val selectedOption = viewModel.selectedOption.value

    val carbType = UserNutritionSettingInformation.ValueType.CARB
    val proteinType = UserNutritionSettingInformation.ValueType.PROTEIN
    val fatType = UserNutritionSettingInformation.ValueType.FAT

    val carb = viewModel.macros[UserNutritionSettingInformation.ValueType.CARB]
    val protein = viewModel.macros[UserNutritionSettingInformation.ValueType.PROTEIN]
    val fat = viewModel.macros[UserNutritionSettingInformation.ValueType.FAT]

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ExposedDropdownMenuBox(
                    expanded = dropDownMenuState,
                    onExpandedChange = { dropDownMenuState = !dropDownMenuState }
                ) {
                    CompositionLocalProvider(LocalTextInputService provides null) {
                        TextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            value = selectedOption.settingsName,
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownMenuState) },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            interactionSource = remember { MutableInteractionSource() }
                        )
                    }
                    ExposedDropdownMenu(
                        expanded = dropDownMenuState,
                        onDismissRequest = { dropDownMenuState = false }) {
                        UserNutritionSettingInformation.Option.values().forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option.settingsName) },
                                onClick = {
                                    viewModel.onEvent(
                                        AddEditUserNutritionSettingsEvent.OnOptionChanged(
                                            option
                                        )
                                    )
                                    dropDownMenuState = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column {
            MacroSlider(
                type = carbType,
                value = carb!!,
                sliderColors = viewModel.getSliderColor().color(),
                difference = viewModel.getDifference(),
                onValueChange = {
                    viewModel.onEvent(
                        AddEditUserNutritionSettingsEvent.MacroValueChanged(
                            UserNutritionSettingInformation.ValueType.CARB,
                            it.toInt()
                        )
                    )
                })
            MacroSlider(
                type = fatType,
                value = fat!!,
                sliderColors = viewModel.getSliderColor().color(),
                difference = viewModel.getDifference(),
                onValueChange = {
                    viewModel.onEvent(
                        AddEditUserNutritionSettingsEvent.MacroValueChanged(
                            UserNutritionSettingInformation.ValueType.FAT,
                            it.toInt()
                        )
                    )
                })
            MacroSlider(
                type = proteinType,
                value = protein!!,
                sliderColors = viewModel.getSliderColor().color(),
                difference = viewModel.getDifference(),
                onValueChange = {
                    viewModel.onEvent(
                        AddEditUserNutritionSettingsEvent.MacroValueChanged(
                            UserNutritionSettingInformation.ValueType.PROTEIN,
                            it.toInt()
                        )
                    )
                })
        }
    }
}