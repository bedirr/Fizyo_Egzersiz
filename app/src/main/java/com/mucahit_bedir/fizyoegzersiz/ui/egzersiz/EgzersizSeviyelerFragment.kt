package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentEgzersizSeviyelerBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel


class EgzersizSeviyelerFragment : Fragment() {
    private lateinit var binding: FragmentEgzersizSeviyelerBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val egzersizViewModel: EgzersizViewModel by navGraphViewModels(
        R.id.egzersiz_graph
    )

    private var egzersizSeviyelerAdapter: EgzersizSeviyelerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEgzersizSeviyelerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        egzersizSeviyelerAdapter = EgzersizSeviyelerAdapter {
            egzersizViewModel.selectedEgzersizSeviye = it
            val action = EgzersizSeviyelerFragmentDirections.actionEgzersizSeviyelerFragmentToEgzersizVideolarFragmen()
            findNavController().navigate(action)
        }
        binding.egzersizSeviyelerRecyclerView.adapter = egzersizSeviyelerAdapter

        egzersizViewModel.selectedEgzersiz?.let {
            egzersizSeviyelerAdapter?.submitList(it.egzersizler)
        }
    }
}