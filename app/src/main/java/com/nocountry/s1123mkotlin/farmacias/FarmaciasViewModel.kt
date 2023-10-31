package com.nocountry.s1123mkotlin.farmacias

import android.app.Application
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class FarmaciasViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val placesClient: PlacesClient = Places.createClient(application)
    var farmacias: List<Place> by mutableStateOf(emptyList())

    suspend fun buscarFarmaciasCercanas() {
        val placeFields = listOf(Place.Field.NAME, Place.Field.ADDRESS)
        val request = FindCurrentPlaceRequest.newInstance(placeFields)

        val hasLocationPermission = checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (hasLocationPermission == PackageManager.PERMISSION_GRANTED) {
            try {
                val response: FindCurrentPlaceResponse = placesClient.findCurrentPlace(request).await()
                if (response != null) {
                    val places = response.placeLikelihoods.map { it.place }
                    // Update the farmacias list with the obtained data
                    farmacias = places
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun checkPermission(permission: String): Int {
        return ContextCompat.checkSelfPermission(getApplication(), permission)
    }
}
