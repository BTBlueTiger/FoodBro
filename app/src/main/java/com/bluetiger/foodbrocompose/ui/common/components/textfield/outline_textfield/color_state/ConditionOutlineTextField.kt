package com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> ConditionOutlineTextField(
    state: ConditionOutlineTextFieldPack<T>,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    suffix : @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        singleLine = true,
        value = state.value.toString().emptyIfZero(),
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        suffix = suffix,
        modifier = Modifier,
        colors = state.colorCase.color(),
        isError = state.isError,
        supportingText = state.supportingText
    )
}

private fun String.emptyIfZero() = if (this == "0") "" else this
