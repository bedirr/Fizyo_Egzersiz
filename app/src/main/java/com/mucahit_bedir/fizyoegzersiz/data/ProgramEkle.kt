package com.mucahit_bedir.fizyoegzersiz.data

import com.mucahit_bedir.fizyoegzersiz.data.web.model.EgzersizListeResponse
import java.util.*

data class ProgramEkle(
    var baslangicTarih: Calendar,
    var haftaSayisi: Int,
    var egzersiz: EgzersizListeResponse.ItemEgzersizler.EgzersizVideo?,
    var isCheckPazartesi: Boolean = false,
    var isCheckSali: Boolean = false,
    var isCheckCarsamba: Boolean = false,
    var isCheckPersembe: Boolean = false,
    var isCheckCuma: Boolean = false,
    var isCheckCumartesi: Boolean = false,
    var isCheckPazar: Boolean = false,
)
