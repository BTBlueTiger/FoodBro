package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases

data  class InputFieldState <T>(
    val value: T,
    val colorCase: OutlineTextFieldColorCases = OutlineTextFieldColorCases.DEFAULT,
    val isError : Boolean = false,
    val supportingText: @Composable() (() -> Unit)? = null
)