package com.mucahit_bedir.fizyoegzersiz.data.local.dao

import androidx.room.*
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi

@Dao
interface EgzersizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEgzersizTakvimi(EgzersizTakvimiList: List<EgzersizTakvimi>)

    @Update
    suspend fun updateEgzersizTakvimi(egzersizTakvimi: EgzersizTakvimi)

    @Delete
    suspend fun deleteEgzersizTakvimi(egzersizTakvimi: EgzersizTakvimi)

    @Query("SELECT * FROM EgzersizTakvimi")
    suspend fun getAllEgzersizTakvimi(): List<EgzersizTakvimi>

    @Query("SELECT * From EgzersizTakvimi WHERE tamamlandimi = :tamamlandimi")
    suspend fun getTamamlandiEgzersizTakvimi(tamamlandimi: Boolean = true): List<EgzersizTakvimi>
}