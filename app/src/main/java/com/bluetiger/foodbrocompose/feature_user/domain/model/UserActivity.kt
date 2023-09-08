package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_activity",
    foreignKeys = [
        ForeignKey(
            entity = UserPersonal::class,
            parentColumns = ["name"],
            childColumns = ["userName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserActivity(
    @PrimaryKey(autoGenerate = true)
    val userActivityId: Long = 0,
    val userName: String = "",
    @ColumnInfo(name = "sleep") val sleep: Float = 0f,
    @ColumnInfo(name = "lightly_active") val lightlyActive: Float = 0f,
    @ColumnInfo(name = "moderately_active") val moderatelyActive: Float = 0f,
    @ColumnInfo(name = "active") val active: Float = 0f,
    @ColumnInfo(name = "very_active") val veryActive: Float = 0f,
    @ColumnInfo(name = "hard_active") val hardActive: Float = 0f,
    @ColumnInfo(name = "pal") val pal: Float = 0f
) {

    operator fun iterator(): Iterator<Float> {
        return listOf(sleep, lightlyActive, moderatelyActive, active, veryActive, hardActive).iterator()
    }

    enum class ValueType(
        val shortTerm: String,
        val description: String,
        val minPalFactor: Float,
        val maxPalFactor: Float,
        val meanPalFactor: Float,
        val examples: String
    ) {
        SLEEP(
            "Sleep",
            "Sleeping",
            0.95f,
            0.95f,
            0.95f,
            "-"
        ),
        LIGHTLY_ACTIVE(
            "Lightly Active",
            "Only Sitting or lying",
            1.2f,
            1.2f,
            1.2f,
            "Reading, watching Tv"
        ),
        MODERATELY_ACTIVE(
            "Moderate Active",
            "Nearly Sitting, little activity",
            1.4f,
            1.5f,
            1.45f,
            "Office activity"
        ),
        ACTIVE(
            "Active",
            "Most Time Sitting, some staying Situations",
            1.6f,
            1.7f,
            1.65f,
            "Students, Driver, Laboratory"
        ),
        VERY_ACTIVE(
            "Very active",
            "Most time staying, walking work",
            1.8f,
            1.9f,
            1.85f,
            "Sellers, Waiters, Craftstmen, HouseWife/Man"
        ),
        HARD_ACTIVE(
            "Hard Active",
            "Hard exhaustive Work",
            2.0f,
            2.4f,
            2.2f,
            "Miners, Farmers, Forest workers, High performance sportler"
        )
    }
}
