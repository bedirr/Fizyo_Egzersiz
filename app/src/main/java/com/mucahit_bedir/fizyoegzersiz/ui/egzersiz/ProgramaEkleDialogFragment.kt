package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.mucahit_bedir.fizyoegzersiz.databinding.DialogFragmentProgramaEkleBinding

class ProgramaEkleDialogFragment : DialogFragment() {

    lateinit var binding: DialogFragmentProgramaEkleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentProgramaEkleBinding.inflate(inflater, container, false)
        return binding.root
    }

}