package com.bluetiger.foodbrocompose.ui.common.components.textfield

import androidx.compose.runtime.State

data class TextFieldState(
    val text: String = "",
    val isEmpty: Boolean = true,
    val isError: Boolean = false,
)