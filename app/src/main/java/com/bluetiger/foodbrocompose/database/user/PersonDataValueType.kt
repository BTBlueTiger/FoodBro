package com.bluetiger.foodbrocompose.database.user

import com.bluetiger.foodbrocompose.R

enum class PersonDataValueType(
    val label: String,
    val placeHolder: String,
) {
    EMAIL("Email", "example@aol.com"),
    WEIGHT("Weight", "80 kg"),
    HEIGHT("Height", "1.80 cm"),
    BIRTHDAY("Birthday", "28.08.1995"),
    GENDER("Gender", "None"),
    PREGNANT("Pregnant", "None"),
    BREAST_FEEDING("Breastfeeding", "None");

    fun getGenderIcon(gender: String) =
        if(gender == "Female") R.drawable.baseline_female_24
        else R.drawable.baseline_male_24
}