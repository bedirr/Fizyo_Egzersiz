package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentEgzersizVideolarBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel

class EgzersizVideolarFragment : Fragment() {
    private lateinit var binding: FragmentEgzersizVideolarBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val egzersizViewModel: EgzersizViewModel by navGraphViewModels(
        R.id.egzersiz_graph
    )

    private var egzersizVideolarAdapter: EgzersizVideolarAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEgzersizVideolarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        egzersizViewModel.setContext(requireContext())
        egzersizVideolarAdapter = EgzersizVideolarAdapter {
            findNavController().navigate(R.id.action_egzersizVideolarFragment_to_programEkleDialogFragment)
        }
        binding.egzersizVideolarRecyclerView.adapter = egzersizVideolarAdapter

        egzersizViewModel.selectedEgzersizSeviye?.let {
            egzersizVideolarAdapter?.submitList(it.videolar)
        }
    }
}