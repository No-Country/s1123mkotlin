package com.nocountry.s1123mkotlin.farmacias

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FarmaciasViewModel : ViewModel() {
    private val _farmaciasData = MutableStateFlow<List<Farmacia>>(emptyList())

    val farmaciasData: StateFlow<List<Farmacia>> = _farmaciasData


    fun cargarDatosFarmacias() {
        val datosFarmacias = obtenerDatosDeFuenteExterna()
        _farmaciasData.value = datosFarmacias
    }

    // Función para obtener datos de una fuente externa (por ejemplo, una API)
    private fun obtenerDatosDeFuenteExterna(): List<Farmacia> {
        // Aquí, simularemos datos de farmacias estáticos para fines de ejemplo
        return listOf(
            Farmacia("Farmacia A", "Dirección A"),
            Farmacia("Farmacia B", "Dirección B"),
            Farmacia("Farmacia C", "Dirección C")
        )
    }
}

data class Farmacia(
    val nombre: String,
    val direccion: String)
