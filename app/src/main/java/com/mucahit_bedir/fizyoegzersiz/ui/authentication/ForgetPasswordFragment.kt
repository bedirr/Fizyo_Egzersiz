package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentForgetPasswordBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel

class ForgetPasswordFragment : Fragment(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentForgetPasswordBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(false)
        binding.restartPasswordButton.setOnClickListener(this)
        auth = Firebase.auth
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.restartPasswordButton -> {
                Firebase.auth.sendPasswordResetEmail(
                    binding.emailEditText.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigateUp()
                    } else {
                        Toast.makeText(
                            this.requireContext(),
                            task.exception?.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


}