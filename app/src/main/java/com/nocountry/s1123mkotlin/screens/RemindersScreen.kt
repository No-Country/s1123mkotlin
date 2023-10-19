package com.nocountry.s1123mkotlin.screens

import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RemindersScreen(
    navController: NavController
) {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }
    val reminders = remember { mutableStateListOf<Reminder>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Recordatorios",
            color = Color.DarkGray,
            modifier = Modifier.padding(16.dp)
        )

        // Selector de fecha y hora personalizado
        DateTimePicker(
            selectedDate = selectedDate,
            onDateSelected = { newDate -> selectedDate = newDate },
            selectedTime = selectedTime,
            onTimeSelected = { newTime -> selectedTime = newTime }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de recordatorios
        RemindersList(reminders)

        // Botón para agregar un nuevo recordatorio
        FloatingActionButton(
            onClick = {
                val newReminder = Reminder(
                    title = "Nuevo Recordatorio",
                    date = selectedDate.time,
                    time = selectedTime.time,
                    repeatInterval = "Diario" // Ajusta esto según tus necesidades
                )
                reminders.add(newReminder)
                selectedDate = Calendar.getInstance()
                selectedTime = Calendar.getInstance()
            }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Recordatorio")
        }
    }
}

@Composable
fun DateTimePicker(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit,
    selectedTime: Calendar,
    onTimeSelected: (Calendar) -> Unit
) {
    // Aquí puedes implementar un selector de fecha y hora personalizado
    // Puedes usar los componentes DatePicker y TimePicker mencionados en la conversación anterior.
}

@Composable
fun RemindersList(reminders: List<Reminder>) {
    LazyColumn {
        items(reminders) { reminder ->
            ReminderCard(reminder = reminder)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Implementa la navegación a la pantalla de detalle del recordatorio
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = reminder.title,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Fecha: ${reminder.date}, Hora: ${reminder.time}",
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Repetición: ${reminder.repeatInterval}",
                color = Color.DarkGray

            )
        }
    }
}




