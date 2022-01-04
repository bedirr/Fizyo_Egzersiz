package com.mucahit_bedir.fizyoegzersiz.ui.calendar

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.DatePicker
import androidx.fragment.app.activityViewModels
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentCalendarBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel
import com.mucahit_bedir.fizyoegzersiz.util.toFormattedString
import java.util.*


class CalendarFragment : Fragment(), DatePickerDialog.OnDateSetListener, View.OnClickListener {
    private lateinit var binding: FragmentCalendarBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var calendarDaysAdapter: CalendarDaysAdapter? = null
    private val datePicker: DatePickerDialog by lazy {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]
        DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)

        listOf(
            binding.selectDateButton
        ).forEach { it.setOnClickListener(this) }
        calendarDaysAdapter = CalendarDaysAdapter {
            //item click listener
        }
        binding.daysRecyclerView.adapter = calendarDaysAdapter

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.selectDateButton -> {
                datePicker.show()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val newDate = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
        binding.selectDateButton.setText(newDate.toFormattedString())

        val dayCount = newDate.getActualMaximum(Calendar.DAY_OF_MONTH)

        val dayList = (1..dayCount).map {
            CalendarDay(
                dayNumber = it,
                date = newDate.apply {
                    set(Calendar.DAY_OF_MONTH, it)
                }.time,
                isThereTraining = it % 3 == 0
            )
        }
        calendarDaysAdapter?.submitList(dayList)
    }

}