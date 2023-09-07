package com.bluetiger.foodbrocompose.feature_user_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_food_bro_list")
data class UserFoodBroListCrossRef(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userEmail: String,
    val listName: String
)
