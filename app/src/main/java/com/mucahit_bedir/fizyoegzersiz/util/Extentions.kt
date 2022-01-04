package com.mucahit_bedir.fizyoegzersiz.util

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toFormattedString(): String {
    return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(this.time)
}