package com.bluetiger.foodbrocompose.ui.common.stateable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.ui.common.selection.Selection
import com.bluetiger.foodbrocompose.ui.common.selection.SelectionGroup
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
/**
 * A simple wrapper class for creating Material3 Compose components with customizable behavior.
 * This class provides a convenient way to create and manage stateful Compose components.
 *
 * Usage example:
 * ```
 * StateAble(
 *     label = "Name",
 *     placeholder = "Enter your name",
 *     modifier = Modifier,
 *     onValueChange = { newValue -> /* handle value change */ }
 * ).TextField()
 * ```
 *
 * The `StateAble.Model` can be used to manage the state and value of the components.
 * It provides a mutable state for the component's state (StateAble.State) and its value.
 */
class StateAble(
    private val value: String = "",
    private val label: String = "",
    private val placeholder: String = "",
    private val modifier: Modifier,
    private val state: State = State.DEFAULT,
    private val onValueChange: (String) -> Unit
) {

    // Possible states for the StateAble component
    enum class State {
        DEFAULT, FILLED, ERROR
    }

    /**
     * Model class for managing the state and value of the StateAble component.
     * This class utilizes a ViewModel to efficiently manage the state and value.
     *
     * @param startValue The initial value for the StateAble component.
     * @param startState The initial state for the StateAble component.
     */
    class Model(startValue: String, startState: State) : ViewModel() {
        // MutableState for the current state and value of the component
        val currentState = mutableStateOf(startState)
        val currentValue = mutableStateOf(startValue)

        // Functions to update value and state
        fun setValue(value: String) = run { currentValue.value = value }
        fun setState(state: State) = run { currentState.value = state }

        // Function to check and update the state to ERROR
        fun errorIfState(state: State): Boolean {
            if (currentState.value == state) {
                currentState.value = State.ERROR
                return true
            }
            return false
        }

        // Check if the state is ERROR
        fun isError() = currentState.value == State.ERROR
    }


    @Composable
    fun TextField() {
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            modifier = modifier,
            colors = getColor(state = state)
        )
    }

    @Composable
    fun DatePicker() {
        var showDialog by remember { mutableStateOf(false) }
        var time by remember { mutableStateOf(0L) }
        var textFieldValue by remember { mutableStateOf("") }

        LaunchedEffect(key1 = time){
            if(time == 0L)
                textFieldValue = ""
            else {
                textFieldValue = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(time))
                onValueChange(textFieldValue)
            }
        }

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder) },
            modifier = modifier,
            colors = getColor(state = state),
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                showDialog = true
                            }
                        }
                    }
                }
        )
        if (showDialog) {
            DatePickerDialog(
                onSelected = { time = it },
                onDismissRequest = { showDialog = false }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DatePickerDialog(
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
                        onSelected.invoke(datePickerState.selectedDateMillis ?: 0)
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


    @Composable
    fun StateAbleSelectionGroup(
        selections: List<Selection>,
        rows: Int,
        heightPerRow: Int = 100,
        widthPerSelection: Int = 100,
    ) {
        SelectionGroup(
            value = value,
            label = label,
            modifier = modifier,
            onValueChange = onValueChange,
            selections = selections,
            rows = rows,
            heightPerRow = heightPerRow,
            widthPerSelection = widthPerSelection
        )
    }


    private fun LocalDate.toInstant() = this.atStartOfDay(ZoneId.systemDefault()).toInstant()


    @Composable
    private fun defaultColor() = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
    )

    @Composable
    private fun errorColor() = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.error,
    )

    @Composable
    private fun filledColor() = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
    )

    @Composable
    private fun getColor(state: State): TextFieldColors = when (state) {
        State.DEFAULT -> defaultColor()
        State.FILLED -> filledColor()
        State.ERROR -> errorColor()
    }

}