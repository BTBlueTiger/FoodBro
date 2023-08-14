package com.bluetiger.foodbrocompose.utility

import android.util.Log
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object ConverterUtility {
    fun localDateToYear(date: LocalDate) = LocalDate.now().year - date.year

    fun longToDate(long: Long) = when (long) {
        0L -> ""
        else ->
            try {
                longToLocalDate(long).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            } catch (e: IllegalArgumentException) {
                Log.e("Long to Simple Date", "$long is not valid")
            }
    }

    fun longToLocalDate(long: Long) = Instant.ofEpochMilli(long).atZone(ZoneId.systemDefault()).toLocalDate()
}