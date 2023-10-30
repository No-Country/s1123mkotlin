package com.nocountry.s1123mkotlin.consultas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConsultasScreenViewModel : ViewModel() {
    private val _consultas = MutableLiveData<List<ConsultaMedica>>()
    val consultas: LiveData<List<ConsultaMedica>> = _consultas

    fun agregarConsulta(consulta: ConsultaMedica) {
        val listaConsultas = _consultas.value.orEmpty().toMutableList()
        listaConsultas.add(consulta)
        _consultas.value = listaConsultas
    }
}
