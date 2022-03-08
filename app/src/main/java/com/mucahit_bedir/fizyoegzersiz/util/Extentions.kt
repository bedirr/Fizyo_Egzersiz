package com.mucahit_bedir.fizyoegzersiz.util

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toFormattedString(): String {
    return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(this.time)
}

fun Calendar.toFormattedYYYYMMDDString(): String {
    return SimpleDateFormat("yyyy:MM:dd", Locale.getDefault()).format(this.time)
}