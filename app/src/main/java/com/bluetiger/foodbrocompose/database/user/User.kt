package com.bluetiger.foodbrocompose.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bluetiger.foodbrocompose.utility.toAge
import com.bluetiger.foodbrocompose.utility.toLocalDate
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.annotation.concurrent.Immutable

@Entity(
    tableName = "user"
)
@Immutable
data class User(
    @PrimaryKey val email: String,
    @ColumnInfo(name = "birthday") val birthday: Long,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "Weight") val weight: Int,
    @ColumnInfo(name = "gender") val gender: String
){

    private fun basalMetabolicMan() = (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * birthday.toLocalDate().toAge()))
    private fun basalMetabolicWoman() = (447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * birthday.toLocalDate().toAge()))

    fun getCaloriesPerDay() = if(gender == "Female") basalMetabolicWoman() else basalMetabolicMan()
    companion object {
        val DEMO = User("", 0L,1,1, "male")
    }
}