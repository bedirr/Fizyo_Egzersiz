package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragmentViewModel : ViewModel() {
    private val auth = Firebase.auth

    private val _loginResponse = MutableLiveData<Pair<Boolean, String>>()
    val loginResponse: LiveData<Pair<Boolean, String>> = _loginResponse

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener { task ->
            _loginResponse.postValue(
                Pair(task.isSuccessful, task.exception?.message.toString())
            )
        }
    }
}