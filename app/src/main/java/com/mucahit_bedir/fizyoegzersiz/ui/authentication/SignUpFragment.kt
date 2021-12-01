package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupButton.setOnClickListener(this)
        auth = Firebase.auth
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.signupButton -> {
                if (binding.againPasswordEditText.text.toString() != binding.passwordEditText.text.toString()) {
                    Toast.makeText(
                        this.requireContext(),
                        "Girdiğiniz parolalar eşleşmedi!",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                auth.createUserWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString(),

                    ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val action =
                            SignUpFragmentDirections.actionSignUpFragmentToHomeFragment()
                        findNavController().navigate(action)

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