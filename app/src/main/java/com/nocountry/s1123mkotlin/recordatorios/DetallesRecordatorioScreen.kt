package com.nocountry.s1123mkotlin.recordatorios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nocuntry.s1123mkotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesRecordatorioScreen(
    recordatorio: Recordatorio,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Detalles del Recordatorio") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(colorResource(id = R.color.fondo_sintomas))
        ) {
            Text(text = "Título: ${recordatorio.titulo}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Descripción: ${recordatorio.description}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ubicación: ${recordatorio.ubicacion}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Fecha: ${recordatorio.fecha}")
        }
    }
}
