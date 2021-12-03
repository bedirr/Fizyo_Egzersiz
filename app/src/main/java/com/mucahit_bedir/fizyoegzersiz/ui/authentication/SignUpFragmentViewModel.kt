package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragmentViewModel : ViewModel() {

    private val auth = Firebase.auth
    private val _signUpResponse = MutableLiveData<Pair<Boolean, String>>()
    val signUpResponse: LiveData<Pair<Boolean, String>> = _signUpResponse


    fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener { task ->
            _signUpResponse.postValue(
                Pair(task.isSuccessful, task.exception?.message.toString())
            )
        }
    }
}