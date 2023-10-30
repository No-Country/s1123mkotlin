package com.nocountry.s1123mkotlin.sintomas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nocuntry.s1123mkotlin.R
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSymptomNoteScreen(
    viewModel: SymptomNotesViewModel,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.White) }
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Agregar Nota de Síntomas") }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(colorResource(id = R.color.fondo_sintomas))
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título de la nota") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Sintomas") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para mostrar el diálogo de selección de color
            Button(
                onClick = { showDialog.value = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Seleccionar Color")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar la nota
            ElevatedButton(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        val newNote = SymptomNote(
                            id = Random.nextInt(),
                            title = title,
                            content = content,
                            backgroundColor = selectedColor
                        )
                        viewModel.addSymptomNote(newNote)

                        // Regresa a la pantalla SintomasScreen al guardar la nota
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(selectedColor)
            ) {
                Text("Guardar", color = Color.Black)
            }
        }

        // Diálogo de selección de color
        if (showDialog.value) {
            Dialog(
                onDismissRequest = { showDialog.value = false }
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(
                            text = "Seleccionar Color de Fondo",
                            modifier = Modifier.padding(16.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            for (color in listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow)) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(color)
                                        .clickable {
                                            selectedColor = color
                                            showDialog.value = false
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddSymptomNoteScreenPreview() {
    // Simula un ViewModel vacío para la vista previa
    val viewModel = SymptomNotesViewModel()
    val navController = rememberNavController()
    AddSymptomNoteScreen(viewModel = viewModel, navController = navController)
}
