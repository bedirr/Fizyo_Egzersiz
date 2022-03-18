package com.mucahit_bedir.fizyoegzersiz.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mucahit_bedir.fizyoegzersiz.data.User


class UserInformationFragmentViewModel: ViewModel() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val dataBaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("Users")
    }
    private val uid: String by lazy{
        auth.currentUser?.uid.toString()
    }

    private val _setUserResponse = MutableLiveData<Boolean>()
    val setUserResponse: LiveData<Boolean> = _setUserResponse

    fun setUser(user: User) {
        if (uid != null){

            dataBaseReference.child(uid).setValue(user).addOnCompleteListener {
                _setUserResponse.postValue(it.isSuccessful)

            }
        }

    }

}