package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.data.ProgramEkle
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi
import com.mucahit_bedir.fizyoegzersiz.databinding.DialogFragmentProgramaEkleBinding
import java.util.*

class ProgramaEkleDialogFragment : DialogFragment(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    private lateinit var binding: DialogFragmentProgramaEkleBinding
    private val egzersizViewModel: EgzersizViewModel by navGraphViewModels(
        R.id.egzersiz_graph
    )
    private var haftaSayisi: Int = 0

    private val adapter by lazy {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayListOf("1 Hafta", "2 Hafta", "3 Hafta", "4 Hafta", "5 Hafta", "6 Hafta")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentProgramaEkleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        egzersizViewModel.setContext(requireContext())
        binding.haftalarSpinner.adapter = adapter

        binding.haftalarSpinner.onItemSelectedListener = this
        listOf(
            binding.tamamButton,
            binding.iptalButton
        ).forEach {
            it.setOnClickListener(this)
        }
        egzersizViewModel.sonucLiveData.observe(viewLifecycleOwner){
            if (it.second.not()){
                Toast.makeText(requireContext(),"Exception",Toast.LENGTH_LONG).show()
            }else{
                findNavController().navigateUp()
            }
        }

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.tamamButton -> {
                //Database e egzersizleri kayıt etme
                //Hangi günler
                val baslangicTarih = Calendar.getInstance()
                val programEkle = ProgramEkle(
                    baslangicTarih = baslangicTarih,
                    haftaSayisi = haftaSayisi,
                    egzersiz = egzersizViewModel.selectedEgzersizVideo,
                    isCheckPazartesi = binding.checkboxPazartesi.isChecked,
                    isCheckSali = binding.checkboxSali.isChecked,
                    isCheckCarsamba = binding.checkboxCarsamba.isChecked,
                    isCheckPersembe = binding.checkboxPersembe.isChecked,
                    isCheckCuma = binding.checkboxCuma.isChecked,
                    isCheckCumartesi = binding.checkboxCumartesi.isChecked,
                    isCheckPazar = binding.checkboxPazar.isChecked
                )
                egzersizViewModel.ekleEgzersiz(programEkle)
            }
            binding.iptalButton -> {
                //ProgramEkle ekranını kapat
                findNavController().navigateUp()
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //seçilen hafta burada bulunacak
        haftaSayisi = p2 + 1
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}