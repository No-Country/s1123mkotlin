package com.nocountry.s1123mkotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nocountry.s1123mkotlin.consultas.ConsultaMedica
import com.nocountry.s1123mkotlin.consultas.ConsultasScreenViewModel
import com.nocuntry.s1123mkotlin.R
import java.util.UUID

@ExperimentalMaterial3Api
@Composable
fun ConsultasScreen(
    navController: NavController,
    viewModel: ConsultasScreenViewModel
) {
    val consultas by viewModel.consultas.observeAsState(emptyList())
    var fecha by remember { mutableStateOf(TextFieldValue()) }
    var nombreMedico by remember { mutableStateOf(TextFieldValue()) }
    var ubicacion by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf("") }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(colorResource(id = R.color.fondo_sintomas))
    ) {
        Text(
            text = "Consultas Médicas",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier.padding(16.dp)
        )

        // Formulario cosnulta médica
        TextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha de la consulta") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black
            )
        )

        TextField(
            value = nombreMedico,
            onValueChange = { nombreMedico = it },
            label = { Text("Nombre del médico") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black
            )
        )

        TextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Ubicación de la clinica u Hospital") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black
            )
        )


        Button(
            onClick = {
                if (fecha.text.isNotEmpty() && nombreMedico.text.isNotEmpty() && ubicacion.text.isNotEmpty()) {
                    val nuevaConsulta = ConsultaMedica(
                        UUID.randomUUID().toString(),
                        fecha.text,
                        nombreMedico.text,
                        ubicacion.text,
                    )
                    viewModel.agregarConsulta(nuevaConsulta)
                    fecha = TextFieldValue()
                    nombreMedico = TextFieldValue()
                    ubicacion = TextFieldValue()
                    viewModel.agregarConsulta(nuevaConsulta)

                    fecha = TextFieldValue(text = "")
                    nombreMedico = TextFieldValue(text = "")
                    ubicacion = TextFieldValue(text = "")

                } else {
                    // Muestra un mensaje de error si algún campo está vacío
                    errorMessage = "Completa todos los campos"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        ) {
            Text("Agregar Consulta", color = colorResource(id = R.color.texto))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de consultas médicas
        LazyColumn {
            items(consultas) { consulta ->
                ConsultaItem(consulta = consulta)
            }
        }
    }
}

@Composable
fun ConsultaItem(consulta: ConsultaMedica) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Implementar la navegación a la pantalla de detalles de consulta
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Fecha: ${consulta.fecha}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Médico: ${consulta.nombreMedico}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ubicación: ${consulta.ubicacion}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}