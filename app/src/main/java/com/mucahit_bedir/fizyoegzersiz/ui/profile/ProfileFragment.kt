package com.mucahit_bedir.fizyoegzersiz.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.data.User
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentForgetPasswordBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentHomeBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentProfileBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel
import com.mucahit_bedir.fizyoegzersiz.ui.authentication.LoginFragmentDirections

class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
    private val profileFragmentViewModel: ProfileFragmentViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        initObserver()
        profileFragmentViewModel.getUserData()
        listOf(
            binding.cikisButton
        ).forEach {
            it.setOnClickListener(this)
        }

    }

    fun initObserver() {
        profileFragmentViewModel.userResponse.observe(viewLifecycleOwner) { user ->

            binding.userFullnameTextView.text = user.isim + " " + user.soyisim
            binding.isimTextView.text = user.isim
            binding.soyisimTextView.text = user.soyisim
            binding.dogumTextView.text = user.dogum
            binding.boyTextView.text = user.boy
            binding.kiloTextView.text = user.kilo
        }
    }


    override fun onClick(view: View?) {

    }


}