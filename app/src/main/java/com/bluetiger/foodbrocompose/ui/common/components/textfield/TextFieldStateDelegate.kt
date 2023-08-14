package com.bluetiger.foodbrocompose.ui.common.components.textfield

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class TextFieldStateDelegate(initialValue: TextFieldState) {
    operator fun getValue(ref: Any?, property: Any): State<TextFieldState> {
        return _value
    }
    private var _value = mutableStateOf(initialValue)
    var value: TextFieldState
        get() = _value.value
        set(newValue) {
            _value.value = newValue
        }
}