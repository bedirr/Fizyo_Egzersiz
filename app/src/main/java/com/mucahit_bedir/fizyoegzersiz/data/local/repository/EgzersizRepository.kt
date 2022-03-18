package com.mucahit_bedir.fizyoegzersiz.data.local.repository

import android.content.Context
import com.mucahit_bedir.fizyoegzersiz.data.local.RoomDB
import com.mucahit_bedir.fizyoegzersiz.data.local.dao.EgzersizDao
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi
import com.mucahit_bedir.fizyoegzersiz.util.toFormattedYYYYMMDDString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class EgzersizRepository(private val context: Context) {

    var egzersizDao: EgzersizDao

    init {
        val roomDB = RoomDB.getDatabase(context)
        egzersizDao = roomDB.egzersizDao()
    }

    suspend fun ekleEgzersiz(list: List<EgzersizTakvimi>) = withContext(Dispatchers.IO) {
        egzersizDao.insertEgzersizTakvimi(list)
    }

    suspend fun getEgzersizHaftalikListe(userId: String): List<EgzersizTakvimi>? =
        withContext(Dispatchers.IO) {
            val baslangicTarih = Calendar.getInstance().apply {
                set(Calendar.DAY_OF_WEEK, 1)
            }.toFormattedYYYYMMDDString()
            val bitisTarih = Calendar.getInstance().apply {
                set(Calendar.DAY_OF_WEEK, 7)
            }.toFormattedYYYYMMDDString()
            egzersizDao.getEgzersizHaftalikListe(
                userId = userId,
                baslangicTarih = baslangicTarih,
                bitisTarih = bitisTarih
            )
        }

}