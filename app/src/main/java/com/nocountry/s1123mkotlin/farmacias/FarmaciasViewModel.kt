package com.nocountry.s1123mkotlin.farmacias

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.nocuntry.s1123mkotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FarmaciasViewModel(application: Application) : AndroidViewModel(application) {
    private val apiKey: String = application.getString(R.string.openrouteservice_apikey)
    var farmacias by mutableStateOf(emptyList<Place>())
    private val apiService: ApiService = getRetrofit(application).create(ApiService::class.java)

    fun findFarmaciasNearUser(userLocation: LatLng, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val route = getRouteToPharmacies(userLocation, context)
            if (route != null) {
                val newFarmacias = updateFarmaciasBasedOnRoute(route)
                withContext(Dispatchers.Main) {
                    farmacias = newFarmacias
                }
            }
        }
    }

    private suspend fun getRouteToPharmacies(userLocation: LatLng, context: Context): RouteResponse? {
        val start = "${userLocation.longitude},${userLocation.latitude}"
        val retrofit = getRetrofit(context)
        val apiService = retrofit.create(ApiService::class.java)
        val response = apiService.getRoute(apiKey, start, "Destination Coordinates Here") // Reemplaza con las coordenadas reales
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    private fun updateFarmaciasBasedOnRoute(route: RouteResponse?): List<Place> {
        val farmacias = mutableListOf<Place>()
        if (route != null) {
            val coordinates = route.coordinates
            if (coordinates != null) {
                // Implementa la lógica para encontrar farmacias cercanas en función de la ruta
                // Puedes utilizar la API de Google Places u otros servicios para esto
                // y agregarlas a la lista de farmacias
            }
        }
        return farmacias
    }
}
