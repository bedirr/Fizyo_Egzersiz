package com.mucahit_bedir.fizyoegzersiz.data.local.repository

import android.content.Context
import com.mucahit_bedir.fizyoegzersiz.data.local.RoomDB
import com.mucahit_bedir.fizyoegzersiz.data.local.dao.EgzersizDao
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EgzersizRepository(private val context: Context) {

    var egzersizDao: EgzersizDao

    init {
        val roomDB = RoomDB.getDatabase(context)
        egzersizDao = roomDB.egzersizDao()
    }

    suspend fun ekleEgzersiz(list: List<EgzersizTakvimi>) = withContext(Dispatchers.IO) {
        egzersizDao.insertEgzersizTakvimi(list)
    }

}