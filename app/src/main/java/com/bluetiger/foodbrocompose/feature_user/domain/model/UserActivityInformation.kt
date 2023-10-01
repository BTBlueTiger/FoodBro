package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.bluetiger.foodbrocompose.R

@Entity(
    tableName = "user_activity",
    foreignKeys = [
        ForeignKey(
            entity = UserPersonalInformation::class,
            parentColumns = ["name"],
            childColumns = ["userName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserActivityInformation(
    @PrimaryKey(autoGenerate = true)
    val userActivityId: Long = 0,
    val userName: String = "",
    @ColumnInfo(name = "sleep") val sleep: Float = 0f,
    @ColumnInfo(name = "lightly_active") val lightlyActive: Float = 0f,
    @ColumnInfo(name = "moderately_active") val moderatelyActive: Float = 0f,
    @ColumnInfo(name = "active") var active: Float = 0f,
    @ColumnInfo(name = "very_active") val veryActive: Float = 0f,
    @ColumnInfo(name = "hard_active") val hardActive: Float = 0f,
    @ColumnInfo(name = "pal") val pal: Float = 0f,
    @ColumnInfo(name = "pre_configured") val preConfigured: String = "Sleep"
) : UserInformation {

    operator fun iterator(): Iterator<Pair<ValueType, Float>> {
        return listOf(
            Pair(ValueType.SLEEP, sleep),
            Pair(ValueType.LIGHTLY_ACTIVE,lightlyActive),
            Pair(ValueType.MODERATELY_ACTIVE, moderatelyActive),
            Pair(ValueType.ACTIVE, active),
            Pair(ValueType.VERY_ACTIVE, veryActive),
            Pair(ValueType.HARD_ACTIVE, hardActive))
            .iterator()
    }

    enum class ValueType(
        val memberParam: String,
        val shortTerm: String,
        val description: String,
        val minPalFactor: Float,
        val maxPalFactor: Float,
        val meanPalFactor: Float,
        val examples: String,
        val icon: Int
    ) {
        SLEEP(
            "sleep",
            "Sleep",
            "Sleeping",
            0.95f,
            0.95f,
            0.95f,
            "-",
            R.drawable.bed
        ),
        LIGHTLY_ACTIVE(
            "lightlyActive",
            "Lightly Active",
            "Only Sitting or lying",
            1.2f,
            1.2f,
            1.2f,
            "Reading, watching Tv, Ill",
            R.drawable.arm_shair
        ),
        MODERATELY_ACTIVE(
            "moderatelyActive",
            "Moderate Active",
            "Nearly Sitting, little activity",
            1.4f,
            1.5f,
            1.45f,
            "Office activity",
            R.drawable.desktop
        ),
        ACTIVE(
            "active",
            "Active",
            "Most Time Sitting, some staying Situations",
            1.6f,
            1.7f,
            1.65f,
            "Students, Driver, Laboratory",
            R.drawable.student
        ),
        VERY_ACTIVE(
            "veryActive",
            "Very active",
            "Most time staying, walking work",
            1.8f,
            1.9f,
            1.85f,
            "Sellers, Waiters, Craftstmen, HouseWife/Man",
            R.drawable.craftsmen
        ),
        HARD_ACTIVE(
            "hardActive",
            "Hard Active",
            "Hard exhaustive Work",
            2.0f,
            2.4f,
            2.2f,
            "Miners, Farmers, Forest workers, High performance sportler",
            R.drawable.medal
        )
    }
}
