package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

@Entity(tableName = "user")
data class UserPersonalInformation(
    @PrimaryKey val name: String = "",
    @ColumnInfo(name = "height") val height: Int = 0,
    @ColumnInfo(name = "Weight") val weight: Int = 0,
    @ColumnInfo(name = "birthday") val birthday: Long = 0L,
    @ColumnInfo(name = "gender") val gender: Gender = Gender.NONE
) : UserInformation {
    enum class ValueType(val label: String, val dataType: KClass<*>, val memberParam: String, val placeHolder: String = "", val unit: String = "") {
        NAME("Name", String::class, "name", "Peter"),
        HEIGHT("Height", Int::class, "height", "180", " cm"),
        WEIGHT("Weight", Int::class, "weight", "80", " kg"),
        BIRTHDAY("Birthday", Long::class, "birthday" ,"28.08.1995"),
        GENDER("Gender" ,Gender::class,  "gender");
    }

    operator fun iterator(): Iterator<Any> {
        return listOf(name, height, weight, birthday, gender).iterator()
    }

    companion object {
        const val MAX_HEIGHT = 251
        const val MAX_WEIGHT = 635
    }

    class InvalidUserException(message: String) : Exception(message)


}

