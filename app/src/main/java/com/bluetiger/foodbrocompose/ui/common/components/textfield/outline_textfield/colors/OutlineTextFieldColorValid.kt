package com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable

class OutlineTextFieldColorValid {
    @Composable
    operator fun invoke() = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
    )
}