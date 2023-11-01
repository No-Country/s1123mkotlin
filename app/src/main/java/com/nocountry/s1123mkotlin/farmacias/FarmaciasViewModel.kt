package com.nocountry.s1123mkotlin.farmacias

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.model.Place
import com.nocuntry.s1123mkotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FarmaciasViewModel(application: Application) : AndroidViewModel(application) {
    private val apiKey: String = application.getString(R.string.openrouteservice_apikey)
    var farmacias: List<Place> by mutableStateOf(emptyList())
    private val apiService: ApiService = getRetrofit(application).create(ApiService::class.java)

    fun findFarmaciaRoutes(start: String, end: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getRoute(apiKey, start, end)
            if (response.isSuccessful) {
                val routeResponse = response.body()
                if (routeResponse != null) {
                    // Procesar los datos del enrutamiento aquí si es necesario
                }
            }
        }
    }

    // Otras funciones para buscar farmacias, obtener la ubicación del usuario, etc.
}
