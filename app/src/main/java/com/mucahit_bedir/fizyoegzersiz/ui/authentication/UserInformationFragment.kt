package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentNotificationBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentUserInformationBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel
import com.mucahit_bedir.fizyoegzersiz.ui.calendar.CalendarDay
import com.mucahit_bedir.fizyoegzersiz.ui.calendar.CalendarDaysAdapter
import com.mucahit_bedir.fizyoegzersiz.ui.profile.ProfileFragmentDirections
import com.mucahit_bedir.fizyoegzersiz.util.toFormattedString
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*


class UserInformationFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentUserInformationBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var dataBaseReference: DatabaseReference

    val cal = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserInformationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(false)
        binding.devamButton.setOnClickListener(this)



        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        binding.dogumTarihiEditText.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(v: View?) {
                DatePickerDialog(
                    requireContext(), dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dogumTarihiEditText.setText(sdf.format(cal.time))
    }

    override fun onClick(v: View?) {
        val action= UserInformationFragmentDirections.actionGlobalHomeFragment()
        findNavController().navigate(action)
    }


}




