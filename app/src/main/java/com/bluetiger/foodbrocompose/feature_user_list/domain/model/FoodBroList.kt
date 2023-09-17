package com.bluetiger.foodbrocompose.feature_user_list.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.bluetiger.foodbrocompose.database.room.common.SerializeAbleList

import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation

@Entity(
    tableName = "food_bro_list",
    foreignKeys = [
        ForeignKey(
            entity = UserPersonalInformation::class,
            parentColumns = ["name"],
            childColumns = ["userName"], // Match the field name in FoodBroList
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FoodBroList(
    @PrimaryKey val listName: String,
    val foodList: SerializeAbleList<Long>,
    val userName: String, // Use this field as the foreign key
    val listType: ListType,
    val userList: SerializeAbleList<String>
)
