package com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import com.example.compose.Color

sealed class OutlineTextFieldColorCases {

    object DEFAULT : OutlineTextFieldColorCases()
    object ERROR : OutlineTextFieldColorCases()
    object VALID : OutlineTextFieldColorCases()
    object PENDING : OutlineTextFieldColorCases()


    @Composable
    fun color() =
        when (this) {
            DEFAULT -> OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            )

            ERROR -> OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.error,
            )

            VALID -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.dark_onGreenContainer,
                focusedLabelColor = Color.dark_onGreenContainer,
                unfocusedBorderColor = Color.dark_onGreenContainer,
            )
            PENDING -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.dark_onYellowContainer,
                focusedLabelColor = Color.light_YellowContainer,
                focusedTextColor = Color.light_YellowContainer
            )
        }

}
