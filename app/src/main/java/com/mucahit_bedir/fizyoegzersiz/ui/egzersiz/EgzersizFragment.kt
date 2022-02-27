package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentEgzersizBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel


class EgzersizFragment : Fragment(), View.OnClickListener{

    private lateinit var binding: FragmentEgzersizBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val egzersizViewModel: EgzersizViewModel by navGraphViewModels(
        R.id.egzersiz_graph
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEgzersizBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var egzersizListeAdapter: EgzersizListeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)
        egzersizListeAdapter = EgzersizListeAdapter {
            egzersizViewModel.selectedEgzersiz = it
            val action = EgzersizFragmentDirections.actionEgzersizFragmentToEgzersizSeviyelerFragment()
            findNavController().navigate(action)
        }
        binding.egzersizListRecyclerView.adapter = egzersizListeAdapter
        initObserver()
        egzersizViewModel.getEgzersizListesi()

    }

    fun initObserver() {
        egzersizViewModel.egzersizListesiResponse.observe(viewLifecycleOwner) {
            egzersizListeAdapter.submitList(it)
        }
    }

    override fun onClick(view: View?) {
        when (view) {
        }
    }
}