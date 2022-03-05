package com.mucahit_bedir.fizyoegzersiz.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mucahit_bedir.fizyoegzersiz.data.local.dao.EgzersizDao
import com.mucahit_bedir.fizyoegzersiz.data.local.model.EgzersizTakvimi

@Database(
    entities = [
        EgzersizTakvimi::class
    ],
    version = 1
)
abstract class RoomDB : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "fizyo_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun egzersizDao(): EgzersizDao
}