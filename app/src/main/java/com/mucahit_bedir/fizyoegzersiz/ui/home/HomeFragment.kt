package com.mucahit_bedir.fizyoegzersiz.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentHomeBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentSignUpBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        initObserver()
        homeViewModel.setContext(requireContext())
        homeViewModel.getUserData()

    }

    fun initObserver() {
        homeViewModel.getUserResponse.observe(viewLifecycleOwner) { user ->
            binding.helloTextView.text = user.isim + " " + user.soyisim

            homeViewModel.getUserEgzersizList()
        }
    }

}