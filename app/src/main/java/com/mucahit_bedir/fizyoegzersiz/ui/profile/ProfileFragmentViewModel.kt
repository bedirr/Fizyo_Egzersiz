package com.mucahit_bedir.fizyoegzersiz.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mucahit_bedir.fizyoegzersiz.data.User


class ProfileFragmentViewModel : ViewModel() {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val uid: String by lazy{
        auth.currentUser?.uid.toString()
    }

    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("Users")
    }

    private val _userResponse = MutableLiveData<User>()
    val userResponse: LiveData<User> = _userResponse

    fun getUserData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)!!
                _userResponse.postValue(user)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}