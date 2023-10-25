package com.nocountry.s1123mkotlin.screens

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.nocuntry.s1123mkotlin.R
import java.util.*
import android.content.Intent
import android.provider.CalendarContract
import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.screens.ReminderEntity
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

@Composable
fun RemindersScreen(
    navController: NavController,
    reminderRepository: ReminderRepository
) {
    val reminders by reminderRepository.allReminders.collectAsState(emptyList())

    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }
    var reminderTitle by remember { mutableStateOf(TextFieldValue()) }

    // Lista mutable para almacenar eventos de calendario
    val calendarEvents = remember { mutableStateListOf<CalendarEvent>() }

    // Inicializar el launcher para abrir la aplicación de calendario
    val calendarLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val eventTitle = reminderTitle.text
            val eventDate = selectedDate.timeInMillis
            val eventTime = selectedTime.timeInMillis

            // Agregar el evento a la lista de eventos de calendario
            calendarEvents.add(CalendarEvent(eventTitle, eventDate, eventTime))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.fondo))
            .padding(16.dp)
    ) {
        Text(
            text = "Recordatorios",
            style = MaterialTheme.typography.h6.copy(color = Color.White),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        DateTimePicker(
            selectedDate = selectedDate,
            onDateSelected = { newDate -> selectedDate = newDate },
            selectedTime = selectedTime,
            onTimeSelected = { newTime -> selectedTime = newTime }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = reminderTitle,
            onValueChange = { reminderTitle = it },
            label = { Text("Nombre del Recordatorio") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.fondoBotones),
                cursorColor = Color.White,
                textColor = Color.White
            )
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Lista de recordatorios
    RemindersList(reminders, navController)

    // Lista de eventos de calendario
    CalendarEventsList(calendarEvents)

    // Botón para agregar un nuevo recordatorio
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.fondoBotones))
    ) {
        FloatingActionButton(
            onClick = {
                // Abre la aplicación del calendario
                val calendarIntent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE, reminderTitle.text)
                    .putExtra(CalendarContract.Events.ALL_DAY, false)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, selectedDate.timeInMillis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, selectedDate.timeInMillis + 3600000) // Duración de 1 hora

                calendarLauncher.launch(calendarIntent)

                // Agregar el recordatorio a la lista
                val newReminder = ReminderEntity(
                    id = UUID.randomUUID().toString(),
                    title = reminderTitle.text,
                    date = selectedDate.timeInMillis,
                    time = selectedTime.timeInMillis,
                    repeatInterval = "Diario"
                )
                runBlocking(Dispatchers.IO) {
                    reminderRepository.insert(newReminder)
                }

                selectedDate = Calendar.getInstance()
                selectedTime = Calendar.getInstance()
                reminderTitle = TextFieldValue()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar Recordatorio"
            )
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
    // Implementa la selección de fecha y hora aquí si es necesario
    // ...
}

@Composable
fun RemindersList(reminders: List<ReminderEntity>, navController: NavController) {
    LazyColumn {
        items(reminders) { reminder ->
            ReminderCard(reminder, navController)
        }
    }
}

@Composable
fun CalendarEventsList(calendarEvents: List<CalendarEvent>) {
    // Implementa la lista de eventos de calendario
    // ...
}

@Composable
fun ReminderCard(reminder: ReminderEntity, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Implementa la navegación a la pantalla de detalle del recordatorio aquí
                navController.navigate("recordatorio/${reminder.id}")
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

data class CalendarEvent(
    val title: String,
    val dateInMillis: Long,
    val timeInMillis: Long
)

