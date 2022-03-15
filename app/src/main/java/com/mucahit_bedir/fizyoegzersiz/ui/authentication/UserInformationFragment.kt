package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.annotation.SuppressLint
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
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
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


        auth= FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        dataBaseReference=FirebaseDatabase.getInstance().getReference("Users")
        binding.devamButton.setOnClickListener {
            val isim = binding.isimEditText.text.toString()
            val soyisim = binding.soyisimEditText.text.toString()
            val dogum = binding.dogumTarihiEditText.text.toString()
            val boy = binding.boyEditText.text.toString()
            val kilo = binding.kiloEditText.text.toString()

            val user = com.mucahit_bedir.fizyoegzersiz.data.User(isim,soyisim,dogum,boy,kilo)
            if (uid != null){

                dataBaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful){
                        val action= UserInformationFragmentDirections.actionGlobalHomeFragment()
                        findNavController().navigate(action)
                    }
                }
            }

        }


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
        TODO("Not yet implemjmklented")
    }


    /*
    val action= UserInformationFragmentDirections.actionGlobalHomeFragment()
    findNavController().navigate(action)
*/


}




