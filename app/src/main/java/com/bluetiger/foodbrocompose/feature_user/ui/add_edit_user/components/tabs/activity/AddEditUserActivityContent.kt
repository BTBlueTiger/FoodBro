package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditUserActivityTabRow(
    viewModel: AddEditUserActivityViewModel = hiltViewModel()
) {
    val userActivityTypeList = listOf(
        ActivitySettingsType.PreConfigured,
        ActivitySettingsType.Customized,
        ActivitySettingsType.HealthConnect
    )
    var dropDownMenuState by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(userActivityTypeList[0]) }

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
                    TextField(
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOption.shortTerm,
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownMenuState) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = dropDownMenuState,
                        onDismissRequest = { dropDownMenuState = false }) {
                        userActivityTypeList.forEach { activityType ->
                            DropdownMenuItem(
                                text = { Text(text = activityType.shortTerm) },
                                onClick = {
                                    selectedOption = activityType
                                    dropDownMenuState = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
        }
        Row {
            selectedOption.Sheet(viewModel)
        }
    }

}

