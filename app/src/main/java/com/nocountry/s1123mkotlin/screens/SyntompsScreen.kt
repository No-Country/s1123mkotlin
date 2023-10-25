package com.nocountry.s1123mkotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nocuntry.medichild.viewmodel.IAViewModel
import com.nocuntry.s1123mkotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SintomasScreen(
    viewModel: IAViewModel,
    reminderRepository: ReminderRepository
) {
    var symptoms by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            item {
                Column {
                    Text(
                        text = "Chat con MediChild_IA",
                        style = androidx.compose.material.MaterialTheme.typography.h6.copy(color = Color.White),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Mostrar conversación
                            ChatConversation(viewModel.chatList)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo para ingresar síntomas
                    TextField(
                        value = symptoms,
                        onValueChange = { symptoms = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(8.dp),
                        textStyle = TextStyle.Default.copy(
                            color = Color.Black
                        ),
                        placeholder = { Text("Ingresa tus síntomas...") }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Botón para enviar síntomas a la IA
                    Button(
                        onClick = {
                            symptoms = ""
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Enviar")
                    }
                }
            }
        }
    }
}

@Composable
fun ChatConversation(messages: List<Pair<String, Color>>) {
    for (message in messages) {
        ChatMessage(message = message.first, color = message.second)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ChatMessage(message: String, color: Color) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.fondo_sintomas)) ,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )
        }
    }
}
