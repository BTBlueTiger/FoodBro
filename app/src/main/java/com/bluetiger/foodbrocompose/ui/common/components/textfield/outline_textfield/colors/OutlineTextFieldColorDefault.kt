package com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable

class OutlineTextFieldColorDefault {
    @Composable
    operator fun invoke(): TextFieldColors =
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        )
}