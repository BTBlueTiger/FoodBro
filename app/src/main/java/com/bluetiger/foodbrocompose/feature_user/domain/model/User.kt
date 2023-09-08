package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.reflect.KClass

@Entity(tableName = "user")
data class User(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "birthday") val birthday: Long,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "Weight") val weight: Int,
    @ColumnInfo(name = "gender") val gender: Gender
) {
    enum class ValueType(val label: String, val dataType: KClass<*>, val placeHolder: String = "", val unit: String = "") {
        NAME("Name", String::class, "Peter"),
        HEIGHT("Height", Int::class, "180", " cm"),
        WEIGHT("Weight", Int::class,"80", " kg"),
        BIRTHDAY("Birthday", Long::class, "28.08.1995"),
        GENDER("Gender", Gender::class);
    }

    operator fun iterator(): Iterator<Any> {
        return listOf(name, height, weight, birthday, gender).iterator()
    }

    companion object {
        const val MAX_HEIGHT = 251
        const val MAX_WEIGHT = 635
        fun emptyUser() = User("", 0, 0, 0, Gender.MALE)
    }

    class InvalidUserException(message: String) : Exception(message)
}

