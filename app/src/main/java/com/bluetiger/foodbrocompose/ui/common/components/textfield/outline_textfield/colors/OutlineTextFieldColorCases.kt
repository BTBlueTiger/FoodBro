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


    @Composable
    fun color() =
        when (this) {
            DEFAULT -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            )

            ERROR -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.error,
            )

            VALID -> OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.dark_onGreenContainer,
            )
        }
}
