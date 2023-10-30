package com.nocountry.s1123mkotlin.recordatorios

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class RecordatoriosViewModel : ViewModel() {
    private val recordatoriosList = mutableStateListOf<Recordatorio>()

    fun getRecordatorios(): List<Recordatorio> = recordatoriosList

    fun addRecordatorio(recordatorio: Recordatorio) {
        recordatoriosList.add(recordatorio)
    }
    fun findRecordatorioByTitulo(titulo: String): Recordatorio? {
        return recordatoriosList.find { it.titulo == titulo }
    }

}
