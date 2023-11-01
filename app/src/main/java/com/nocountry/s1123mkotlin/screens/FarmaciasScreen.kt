package com.nocountry.s1123mkotlin.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.model.Place
import com.nocountry.s1123mkotlin.farmacias.ApiService
import com.nocountry.s1123mkotlin.farmacias.FarmaciasViewModel
import com.nocountry.s1123mkotlin.farmacias.RouteResponse
import com.nocountry.s1123mkotlin.farmacias.getRetrofit
import com.nocuntry.s1123mkotlin.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FarmaciasScreen(
    navController: NavController,
    viewModel: FarmaciasViewModel
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val coroutineScope = rememberCoroutineScope()

    val mapView = remember { MapView(context) }
    var map: GoogleMap? by remember { mutableStateOf(null) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            getLastLocationAndSearchFarmacias(fusedLocationClient, viewModel, context, coroutineScope, mapView)
        } else {
            // Handle permission denied
        }
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
    }

    val getNearbyPharmacies = {
        val hasLocationPermission = locationPermissionState.hasPermission
        if (hasLocationPermission) {
            getLastLocationAndSearchFarmacias(fusedLocationClient, viewModel, context, coroutineScope, mapView)
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

    FarmaciasList(farmacias = viewModel.farmacias, context)
}
private fun getLastLocationAndSearchFarmacias(
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: FarmaciasViewModel,
    context: Context,
    coroutineScope: CoroutineScope,
    mapView: MapView
) {
    val permissionStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val latLng = LatLng(location.latitude, location.longitude)
                coroutineScope.launch {
                    val route = getRouteToPharmacies(latLng, context)
                    if (route != null) {
                        val farmacias = updateFarmaciasBasedOnRoute(route)
                        viewModel.farmacias = farmacias
                        mapView.getMapAsync { googleMap ->
                            googleMap.clear()
                            val userLocation = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
                            googleMap.moveCamera(userLocation)
                        }
                    }
                }
            } else {
                // Handle the case where location cannot be obtained
            }
        }
    } else {
        // Handle lack of permissions
    }
}
private suspend fun getRouteToPharmacies(latLng: LatLng, context: Context): RouteResponse? {
    val apiKey = context.getString(R.string.openrouteservice_apikey)
    val start = "${latLng.longitude},${latLng.latitude}"

    val retrofit = getRetrofit(context)
    val apiService = retrofit.create(ApiService::class.java)

    val response = apiService.getRoute(apiKey, start, "Destination Coordinates Here")
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
            // Implement logic to find nearby pharmacies based on the route
            // You can use the Google Places API or other services for this
            // and add them to the farmacias list
        }
    }
    return farmacias
}

@Composable
fun FarmaciasList(farmacias: List<Place>, context: Context) {
    LazyColumn {
        items(farmacias) { farmacia ->
            FarmaciaItem(farmacia, context)
        }
    }
}

@Composable
fun FarmaciaItem(farmacia: Place, context: Context) {
    val apiKey = context.getString(R.string.openrouteservice_apikey)
    val latLng = LatLng(farmacia.latLng?.latitude ?: 0.0, farmacia.latLng?.longitude ?: 0.0)

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

            MapViewComposable(latLng, apiKey, context)
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun MapViewComposable(latLng: LatLng, apiKey: String, context: Context) {
    var mapView by remember { mutableStateOf<MapView?>(null) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        mapView = MapView(context)
        mapView?.onCreate(null)
        mapView?.getMapAsync { googleMap ->
            googleMap.isMyLocationEnabled = true
            val userLocation = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
            googleMap.moveCamera(userLocation)
        }

        onDispose {
            mapView?.onDestroy()
        }
    }

    LaunchedEffect(Unit) {
        mapView?.onResume()
    }

    AndroidView({ mapView!! })
}
