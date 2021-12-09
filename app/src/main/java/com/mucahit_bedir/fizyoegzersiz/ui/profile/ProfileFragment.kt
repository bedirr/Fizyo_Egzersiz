package com.mucahit_bedir.fizyoegzersiz.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentForgetPasswordBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentHomeBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentProfileBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel
import com.mucahit_bedir.fizyoegzersiz.ui.authentication.LoginFragmentDirections

class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
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
        listOf(
            binding.editProfileButton,
            binding.settingsButton,
            binding.locationButton,
            binding.cikisButton
        ).forEach {
            it.setOnClickListener(this)
        }

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.editProfileButton -> {
                val action = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
                findNavController().navigate(action)
            }
            binding.settingsButton -> {
                val action=ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
                findNavController().navigate(action)
            }

            binding.locationButton->{
                val action=ProfileFragmentDirections.actionProfileFragmentToLocationFragment()
                findNavController().navigate(action)
            }
        }
    }


}