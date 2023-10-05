package com.nocountry.s1123mkotlin.model

import RxNormApiService
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MedicationList(private val apiService: RxNormApiService) {
    suspend fun getMedicamentList(nombre: String): State<List<MedicationResponse>> {
        val medicamentListState = mutableStateOf<List<MedicationResponse>>(emptyList())

        // Realizar la llamada a la API para buscar medicamentos por nombre
        val response = apiService.buscarMedicamentoPorNombre(nombre)

        if (response.isSuccessful) {
            val medicamentos = response.body()
            if (medicamentos != null) {
                withContext(Dispatchers.Main) {
                    medicamentListState.value = medicamentos
                }
            }
        }

        return medicamentListState
    }
}
