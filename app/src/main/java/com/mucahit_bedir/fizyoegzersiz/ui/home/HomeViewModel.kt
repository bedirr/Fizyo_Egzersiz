package com.mucahit_bedir.fizyoegzersiz.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mucahit_bedir.fizyoegzersiz.data.User
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi
import com.mucahit_bedir.fizyoegzersiz.data.local.repository.EgzersizRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val uid: String by lazy {
        auth.currentUser?.uid.toString()
    }

    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("Users")
    }
    lateinit var egzersizRepository: EgzersizRepository

    private val _getUserResponse = MutableLiveData<User>()
    val getUserResponse: LiveData<User> = _getUserResponse

    private val _egzersizTakvimi = MutableLiveData<List<EgzersizTakvimi>>()
    val egzersizTakvimi: LiveData<List<EgzersizTakvimi>> = _egzersizTakvimi

    fun setContext(context: Context) {
        egzersizRepository = EgzersizRepository(context)
    }

    fun getUserData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)!!
                _getUserResponse.postValue(user)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getUserEgzersizList() {
        viewModelScope.launch {
            try {
                val list = egzersizRepository.getEgzersizHaftalikListe(uid)
                _egzersizTakvimi.postValue(list ?: listOf())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}