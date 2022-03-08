package com.mucahit_bedir.fizyoegzersiz.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EgzersizTakvimi(
    @ColumnInfo(name = "tarih") val tarih: String,
    @ColumnInfo(name = "egzersizAdi") val egzersizAdi: String?,
    @ColumnInfo(name = "videoURL") val videoURL: String?,
    @ColumnInfo(name = "tamamlandimi") val tamamlandimi: Boolean?
){
    @PrimaryKey(autoGenerate = true) var uid: Int?=-1
}

