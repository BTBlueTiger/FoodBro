package com.bluetiger.foodbrocompose.utility

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Long.toLocalDate() = Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()

fun LocalDate.toAge() = LocalDate.now().year - this.year