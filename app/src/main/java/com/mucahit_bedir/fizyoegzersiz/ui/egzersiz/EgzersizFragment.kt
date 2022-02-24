package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentEgzersizBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel


class EgzersizFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentEgzersizBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEgzersizBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        listOf(
            binding.belAgrisiCardviewButton,
            binding.boyunAgrisiCardviewButton,
            binding.dizAgrisiCardviewButton,
            binding.omuzAgrisiCardviewButton,
            binding.sirtAgrisiCardviewButton
        ).forEach {
            it.setOnClickListener(this)
        }

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.belAgrisiCardviewButton -> {
                val action = EgzersizFragmentDirections.actionEgzersizFragmentToBelAgrisiFragment()
                findNavController().navigate(action)
            }
            binding.boyunAgrisiCardviewButton -> {
                val action = EgzersizFragmentDirections.actionEgzersizFragmentToBoyunAgrisiFragment()
                findNavController().navigate(action)
            }
            binding.dizAgrisiCardviewButton->{
                val action=EgzersizFragmentDirections.actionEgzersizFragmentToDizAgrisiFragment()
                findNavController().navigate(action)
            }
            binding.omuzAgrisiCardviewButton->{
                val action=EgzersizFragmentDirections.actionEgzersizFragmentToOmuzAgrisiFragment()
                findNavController().navigate(action)
            }
            binding.sirtAgrisiCardviewButton->{
                val action=EgzersizFragmentDirections.actionEgzersizFragmentToSirtAgrisiFragment()
                findNavController().navigate(action)
            }
        }
    }
}