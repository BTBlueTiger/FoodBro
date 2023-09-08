package com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state

import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases

data  class ConditionOutlineTextFieldPack <T>(
    val value: T,
    val colorCase: OutlineTextFieldColorCases = OutlineTextFieldColorCases.DEFAULT,
    val isError : Boolean = false,
    val isValid : Boolean = false,
    val supportingText: @Composable() (() -> Unit)? = null
) {
    fun <U> toValue(): U {
        return value as U
    }
}