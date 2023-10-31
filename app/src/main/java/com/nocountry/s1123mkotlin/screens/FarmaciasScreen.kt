package com.nocountry.s1123mkotlin.screens

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.nocountry.s1123mkotlin.farmacias.FarmaciasViewModel
import com.nocuntry.s1123mkotlin.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.lifecycle.viewModelScope as viewModelScope1

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FarmaciasScreen(
    navController: NavController,
    viewModel: FarmaciasViewModel
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            getLastLocationAndSearchPharmacies(fusedLocationClient, viewModel)
        } else {
            // Handle permission denied
        }
    }
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val coroutineScope = rememberCoroutineScope()
    val application = context.applicationContext as Application

    val viewModel = remember {
        FarmaciasViewModel(application)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(colorResource(id = R.color.fondo_sintomas))
    ) {
        Text(
            text = "Farmacias Cercanas",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        val getNearbyPharmacies = {
            val hasLocationPermission = locationPermissionState.hasPermission
            if (hasLocationPermission) {
                getLastLocationAndSearchPharmacies(fusedLocationClient, viewModel)
            } else {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        if (locationPermissionState.hasPermission) {
            Button(
                onClick = { getNearbyPharmacies() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Buscar Farmacias Cercanas")
            }
        } else {
            Text(
                text = "Necesitas permisos de ubicación para buscar farmacias cercanas.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = { locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Solicitar Permisos de Ubicación")
            }
        }

        FarmaciasList(farmacias = viewModel.farmacias)
    }
}

@Composable
fun FarmaciasList(farmacias: List<Place>) {
    LazyColumn {
        items(farmacias) { farmacia ->
            FarmaciaItem(farmacia)
        }
    }
}

@Composable
fun FarmaciaItem(farmacia: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = farmacia.name ?: "Nombre Desconocido",
                style = MaterialTheme.typography.h6
            )

            Text(
                text = farmacia.address ?: "Dirección Desconocida",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

private fun getLastLocationAndSearchPharmacies(
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: FarmaciasViewModel
) {
    val context = viewModel.getApplication<Application>()
    val permissionStatus = viewModel.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
        viewModel.viewModelScope1.launch {
            try {
                val location = fusedLocationClient.lastLocation.await()
                if (location != null) {
                    val latLng = LatLng(location.latitude, location.longitude)
                    val farmacias = viewModel.buscarFarmaciasCercanas()
                } else {
                    // Manejar el caso en el que no se pudo obtener la ubicación
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    } else {
        // Manejar la falta de permisos
    }
}