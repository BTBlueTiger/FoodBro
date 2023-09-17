package com.bluetiger.foodbrocompose.ui.common.components.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.ZoneId


private fun LocalDate.toInstant() = this.atStartOfDay(ZoneId.systemDefault()).toInstant()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    max: Int = 0,
    onSelected: (Long) -> Unit,
    onDismissRequest: () -> Unit
) {
    val now = LocalDate.now()
    val sixteenYearsBefore = now.minusYears(max.toLong())
    val instantMax = sixteenYearsBefore.atStartOfDay(ZoneId.systemDefault()).toInstant()

    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = sixteenYearsBefore.toInstant().toEpochMilli()
    )
    androidx.compose.material3.DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                    onSelected.invoke(datePickerState.selectedDateMillis ?: 0L)
                }
            ) {
                Text("Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            dateValidator = { timestamp ->
                timestamp < instantMax.toEpochMilli()
            },
        )

    }
}