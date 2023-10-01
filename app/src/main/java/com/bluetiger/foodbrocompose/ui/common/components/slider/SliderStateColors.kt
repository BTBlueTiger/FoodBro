package com.bluetiger.foodbrocompose.ui.common.components.slider

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.ui.theme.Color

sealed class SliderStateColors {
    object DEFAULT : SliderStateColors()
    object ERROR : SliderStateColors()
    object VALID : SliderStateColors()
    object PENDING : SliderStateColors()

    @Composable
    fun color() =
        when (this) {
            DEFAULT -> {
                SliderDefaults.colors()
            }
            ERROR -> {
                SliderDefaults.colors(
                    activeTrackColor = MaterialTheme.colorScheme.onError
                )
            }
            VALID -> {
                SliderDefaults.colors(
                    activeTrackColor = Color.dark_onGreenContainer
                )
            }
            PENDING -> {
                SliderDefaults.colors(
                    activeTrackColor = Color.dark_onYellowContainer
                )
            }
        }

}