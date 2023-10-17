package com.nocountry.s1123mkotlin.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nocountry.s1123mkotlin.ViewModel.ReminderViewModel

@Composable
fun RemindersScreen(viewModel: ReminderViewModel) {
    val reminders = viewModel.getReminders()

    // Lista de recordatorios
    LazyColumn {
        items(reminders) { reminder ->
            ReminderItem(reminder)
        }
    }

    // Botones de acción
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            // Agregar nuevo recordatorio
        }) {
            Text("Agregar recordatorio")
        }

        Button(onClick = {
            // Editar recordatorio
        }) {
            Text("Editar recordatorio")
        }

        Button(onClick = {
            // Eliminar recordatorio
        }) {
            Text("Eliminar recordatorio")
        }
    }
}
@Composable
fun ReminderItem(reminder: Reminder) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(reminder.title)
        Text(reminder.date.toString())
        Text(reminder.time.toString())
    }
}

