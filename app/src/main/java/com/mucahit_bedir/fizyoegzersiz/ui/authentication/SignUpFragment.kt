package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentSignUpBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel


class SignUpFragment : Fragment(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpFragmentViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        sharedViewModel.setBottomNavVisibility(false)
        binding.signupButton.setOnClickListener(this)
        auth = Firebase.auth

        initObserver()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.signupButton -> {
                if (binding.againPasswordEditText.text.toString() !=
                    binding.passwordEditText.text.toString()
                ) {
                    val text = "Şifreleriniz eşleşmedi!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(requireContext(), text, duration)
                    toast.show()
                }
                viewModel.signUp(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
        }
    }

    fun initObserver() {
        viewModel.signUpResponse.observe(viewLifecycleOwner) {
            if (it.first) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToUserInformationFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    this.requireContext(),
                    it.second,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}
