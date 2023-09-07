package com.bluetiger.foodbrocompose.feature_user_list.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Timestamp

import com.bluetiger.foodbrocompose.feature_user.domain.model.User

@Entity(
    tableName = "food_bro_list",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["email"],
            childColumns = ["userEmail"], // Match the field name in FoodBroList
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FoodBroList(
    @PrimaryKey val listName: String,
    val foodList: List<Long>,
    val userEmail: String, // Use this field as the foreign key
    val listType: ListType
)
