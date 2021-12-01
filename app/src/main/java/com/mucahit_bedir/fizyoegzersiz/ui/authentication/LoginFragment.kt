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
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOf(
            binding.girisButton,
            binding.kayitButton,
            binding.forgetPasswordTextView
        ).forEach {
            it.setOnClickListener(this)
        }

        auth = Firebase.auth
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.girisButton -> {
                auth.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                            findNavController().navigate(action)

                        } else {
                            val text = task.exception?.message
                            val duration = Toast.LENGTH_SHORT

                            val toast = Toast.makeText(this.requireContext(), text, duration)
                            toast.show()
                        }
                    }
            }
            binding.kayitButton -> {
                val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
                findNavController().navigate(action)
            }
            binding.forgetPasswordTextView -> {
                val action = LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment()
                findNavController().navigate(action)
            }
        }

    }

}