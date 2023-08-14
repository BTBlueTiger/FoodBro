package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.bluetiger.foodbrocompose.R

enum class Gender(val displayString: String, val image: Int = 0) {
    FEMALE("Female", R.drawable.baseline_female_24),
    MALE("Male", R.drawable.baseline_male_24),
    NONE("None")
}