package com.mucahit_bedir.fizyoegzersiz.ui.egzersiz

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.FirebaseFirestore
import com.mucahit_bedir.fizyoegzersiz.data.ProgramEkle
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi
import com.mucahit_bedir.fizyoegzersiz.data.local.repository.EgzersizRepository
import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import com.mucahit_bedir.fizyoegzersiz.util.toFormattedYYYYMMDDString
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class EgzersizViewModel : ViewModel() {

    val db = FirebaseFirestore.getInstance()
    lateinit var egzersizRepository: EgzersizRepository

    var selectedEgzersiz: EgzersizListeResponse? = null
    var selectedEgzersizSeviye: EgzersizListeResponse.ItemEgzersizler? = null
    var selectedEgzersizVideo: EgzersizListeResponse.ItemEgzersizler.EgzersizVideo? = null

    private val _egzersizListesiResponse = MutableLiveData<ArrayList<EgzersizListeResponse>>()
    val egzersizListesiResponse: LiveData<ArrayList<EgzersizListeResponse>> =
        _egzersizListesiResponse

    private val _sonucLiveData = MutableLiveData<Pair<String, Boolean>>()
    val sonucLiveData: LiveData<Pair<String, Boolean>> =
        _sonucLiveData

    fun setContext(context: Context) {
        egzersizRepository = EgzersizRepository(context)
    }


    fun addDataInFireStore() {
        db.collection("fizyoEgzersiz").document("rahatsizliklar")
            .set(EgzersizListeResponse.mockEgzersizListeResponse())
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error writing document", e) }
    }

    fun ekleEgzersiz(programEkle: ProgramEkle) {
        viewModelScope.launch {
            try {
                val tempList = arrayListOf<EgzersizTakvimi>()
                val calendar = programEkle.baslangicTarih
                for (i in 0..(programEkle.haftaSayisi * 7)) {
                    val egzersizTakvimi = EgzersizTakvimi(
                        tarih = calendar.toFormattedYYYYMMDDString(),
                        egzersizAdi = programEkle.egzersiz?.name ?: "",
                        videoURL = programEkle.egzersiz?.videoURL ?: "",
                        tamamlandimi = false
                    )
                    when (calendar.get(Calendar.DAY_OF_MONTH)) {
                        1 -> {
                            if (programEkle.isCheckPazartesi)
                                tempList.add(egzersizTakvimi)
                        }
                        2 -> {
                            if (programEkle.isCheckSali)
                                tempList.add(egzersizTakvimi)
                        }
                        3 -> {
                            if (programEkle.isCheckCarsamba)
                                tempList.add(egzersizTakvimi)
                        }
                        4 -> {
                            if (programEkle.isCheckPersembe)
                                tempList.add(egzersizTakvimi)
                        }
                        5 -> {
                            if (programEkle.isCheckCuma)
                                tempList.add(egzersizTakvimi)
                        }
                        6 -> {
                            if (programEkle.isCheckCumartesi)
                                tempList.add(egzersizTakvimi)
                        }
                        7 -> {
                            if (programEkle.isCheckPazar)
                                tempList.add(egzersizTakvimi)
                        }
                    }
                    calendar.add(Calendar.DAY_OF_YEAR, 1)
                }
                egzersizRepository.ekleEgzersiz(tempList)
                _sonucLiveData.postValue(Pair("Programa Eklendi", true))
            } catch (e: Exception) {
                _sonucLiveData.postValue(Pair("Programa Eklendi", false))
            }
        }
    }

    fun getEgzersizListesi() {
        _egzersizListesiResponse.postValue(EgzersizListeResponse.mockEgzersizListeResponse())
        /*
            db.collection("fizyoEgzersiz")
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    try {

                        if (task.isSuccessful) {
                            val result = task.result?.documents?.get(0)?.data?.get("liste")
                            if (result != null) {
                                val tempListeResponse = arrayListOf<EgzersizListeResponse>()
                                (result as List<Map<String, String>>).forEach {
                                    *//*tempListeResponse.add(
                                    EgzersizListeResponse(
                                        name = it["name"].toString(),
                                        imageURL = it.get("imageURL").toString()
                                    )
                                )*//*
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
            }*/
    }

}
