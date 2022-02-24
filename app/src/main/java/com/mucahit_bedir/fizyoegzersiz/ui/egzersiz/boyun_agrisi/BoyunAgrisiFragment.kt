package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz.boyun_agrisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentBoyunAgrisiBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentHomeBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel


class BoyunAgrisiFragment : Fragment() {

    private lateinit var binding: FragmentBoyunAgrisiBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoyunAgrisiBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        /*listOf(
            binding.birinciSeviyeBoyunButton,
            binding.ikinciSeviyeBoyunButton,
            binding.ucuncuSeviyeBoyunButton
        ).forEach {
            it.setOnClickListener(this)
        }
         */
    }

   /* override fun onClick(view: View?)
    {
        when{

        }
    }
   */
}