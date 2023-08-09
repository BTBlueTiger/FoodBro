package com.bluetiger.foodbrocompose.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.concurrent.Immutable

@Entity(
    tableName = "user"
)
@Immutable
data class User(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "birthday") val birthday: Long? = null,
    @ColumnInfo(name = "height") val height: Int? = null,
    @ColumnInfo(name = "Weight") val weight: Int? = null,
    @ColumnInfo(name = "gender") val gender: String? = null
)