package com.nocountry.s1123mkotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nocountry.s1123mkotlin.sintomas.SymptomNote
import com.nocountry.s1123mkotlin.sintomas.SymptomNotesViewModel
import com.nocuntry.s1123mkotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SintomasScreen(
    viewModel: SymptomNotesViewModel,
    navController: NavController
) {
    val symptomNotesLiveData = viewModel.getSymptomNotesLiveData()

    // Observa los cambios en la lista de notas de síntomas
    val symptomNotes by symptomNotesLiveData.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(colorResource(id = R.color.fondo_sintomas))
    ) {
        TopAppBar(
            title = { Text("Notas de Síntomas") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(symptomNotes ?: emptyList()) { note ->
                SymptomNoteItem(
                    symptomNote = note,
                    onItemClick = {
                        navController.navigate("sintoma/${note.id}")
                    }
                )
            }
        }
    }

    // Añadir una nueva nota
    FloatingActionButton(
        onClick = {
            // Navegar a la pantalla para agregar una nueva nota
            navController.navigate("add_sintoma")
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Nueva Nota",
        )
    }
}

@Composable
fun SymptomNoteItem(
    symptomNote: SymptomNote,
    onItemClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .zIndex(4f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = symptomNote.title,
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = symptomNote.content,
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Composable
fun SymptomNoteCard(
    note: SymptomNote,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .zIndex(4f)
            .clickable {
                navController.navigate("sintoma_detail/${note.id}")
            },
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = note.content,
                style = androidx.compose.material.MaterialTheme.typography.h6.copy(color = Color.White)
            )
        }
    }
}
