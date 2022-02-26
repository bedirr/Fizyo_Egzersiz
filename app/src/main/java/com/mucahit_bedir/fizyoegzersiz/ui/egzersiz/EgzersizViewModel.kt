package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse


class EgzersizViewModel : ViewModel() {

    val db = FirebaseFirestore.getInstance()

    private val _egzersizListesiResponse = MutableLiveData<ArrayList<EgzersizListeResponse>>()
    val egzersizListesiResponse: LiveData<ArrayList<EgzersizListeResponse>> =
        _egzersizListesiResponse


    fun getEgzersizListesi() {
        db.collection("fizyoEgzersiz")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                try {

                    if (task.isSuccessful) {
                        val result = task.result?.documents?.get(0)?.data?.get("liste")
                        if (result != null) {
                            val tempListeResponse = arrayListOf<EgzersizListeResponse>()
                            (result as List<Map<String, String>>).forEach {
                                tempListeResponse.add(
                                    EgzersizListeResponse(
                                        name = it["name"].toString(),
                                        imageURL = it.get("imageURL").toString()
                                    )
                                )
                            }
                            _egzersizListesiResponse.postValue(tempListeResponse)
                        }
                    } else {
                        Log.w("TAG", "Error getting documents.", task.exception)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }).addOnFailureListener {
                it.printStackTrace()
            }
    }

}
