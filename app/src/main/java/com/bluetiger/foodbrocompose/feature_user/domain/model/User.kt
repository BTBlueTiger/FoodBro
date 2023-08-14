package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "birthday") val birthday: Long,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "Weight") val weight: Int,
    @ColumnInfo(name = "gender") val gender: Gender
) {
    enum class ValueType(val label: String, val placeHolder: String = "", val unit: String = "") {
        EMAIL("Email", "example@gmx.com"),
        HEIGHT("Height", "180", " cm"),
        WEIGHT("Weight", "80", " kg"),
        BIRTHDAY("Birthday", "28.08.1995"),
        GENDER("Gender");
    }



    companion object {
        const val MAX_HEIGHT = 251
        const val MAX_WEIGHT = 635
    }
}

class InvalidUserException(message: String) : Exception(message)

