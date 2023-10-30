package com.nocountry.s1123mkotlin.sintomas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SintomaDetail(
    noteId: Int, // ID de la nota de síntoma que se va a mostrar/editar
    viewModel: SymptomNotesViewModel,
    navController: NavController
) {
    // Obtener la nota de síntoma por su ID
    val symptomNote = viewModel.getSymptomNoteById(noteId)

    var title by remember { mutableStateOf(symptomNote?.title ?: "") }
    var content by remember { mutableStateOf(symptomNote?.content ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Contenido") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón para guardar la nota
            Button(onClick = {
                val updatedNote = symptomNote?.copy(title = title, content = content)
                if (updatedNote != null) {
                    viewModel.updateSymptomNote(updatedNote)
                }
                navController.popBackStack()
            }) {
                Text("Guardar")
            }

            // Botón para eliminar la nota
            Button(onClick = {
                symptomNote?.let { viewModel.deleteSymptomNote(it) }
                navController.popBackStack()
            }) {
                Text("Eliminar")
            }
        }
    }
}

