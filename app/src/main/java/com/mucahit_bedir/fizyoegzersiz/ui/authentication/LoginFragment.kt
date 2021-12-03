package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentLoginBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel


class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private val viewModel: LoginFragmentViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        sharedViewModel.setBottomNavVisibility(false)

        listOf(
            binding.girisButton,
            binding.kayitButton,
            binding.forgetPasswordTextView
        ).forEach {
            it.setOnClickListener(this)
        }
        initObserver()

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.girisButton -> {
                viewModel.login(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()
                )
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

    fun initObserver() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            if (it.first) {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(
                    this.requireContext(),
                    it.second,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}