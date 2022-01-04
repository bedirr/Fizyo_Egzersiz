package com.mucahit_bedir.fizyoegzersiz.util

import androidx.recyclerview.widget.DiffUtil
import com.mucahit_bedir.fizyoegzersiz.ui.calendar.CalendarDay

class GenericDiffUtil<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is CalendarDay -> {
                oldItem as CalendarDay == newItem
            }
            else -> false
        }
    }
}