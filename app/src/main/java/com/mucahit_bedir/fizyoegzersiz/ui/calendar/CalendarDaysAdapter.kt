package com.mucahit_bedir.fizyoegzersiz.ui.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mucahit_bedir.fizyoegzersiz.databinding.ItemViewSelectDaysBinding
import com.mucahit_bedir.fizyoegzersiz.util.GenericDiffUtil
import java.text.SimpleDateFormat

class CalendarDaysAdapter(private val listAdapterListener: (CalendarDay) -> Unit) :
    ListAdapter<CalendarDay, CalendarDaysAdapter.CalendarDaysViewHolder>(GenericDiffUtil<CalendarDay>()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDaysViewHolder {
        return CalendarDaysViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CalendarDaysViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CalendarDaysViewHolder(private val binding: ItemViewSelectDaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalendarDay?) {
            with(item) {
                binding.dayNumberTextView.text = this?.dayNumber.toString()
                val outFormat = SimpleDateFormat("EEEE")
                val goal: String = outFormat.format(this?.date)
                binding.dayNameTextView.text = goal
                binding.isThereTrainingView.visibility =
                    if (this?.isThereTraining == true) View.VISIBLE else View.INVISIBLE
            }
        }

        companion object {
            fun from(parent: ViewGroup): CalendarDaysViewHolder {
                val binding = ItemViewSelectDaysBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CalendarDaysViewHolder(binding)
            }
        }
    }
}
