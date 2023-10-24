package com.nocountry.s1123mkotlin.screens

import android.icu.util.Calendar
import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nocountry.c1322ftkotlin.model.CalendarRepository.getCalendarId
import com.nocuntry.s1123mkotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.UUID

@Composable
fun RemindersScreen(
    navController: NavController,
    reminderRepository: ReminderRepository
) {
    val reminders by reminderRepository.allReminders.collectAsState(emptyList())

    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }

    var isDatePickerDialogVisible by remember { mutableStateOf(false) }
    var isTimePickerDialogVisible by remember { mutableStateOf(false) }

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
            modifier = Modifier
                .padding(16.dp)
        )

        DateTimePicker(
            selectedDate = selectedDate,
            onDateSelected = { newDate -> selectedDate = newDate },
            selectedTime = selectedTime,
            onTimeSelected = { newTime -> selectedTime = newTime }
        )


        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        RemindersList(reminders, navController)

        // Botón para agregar un nuevo recordatorio
        Box(
            modifier = Modifier.fillMaxSize()
        )
        {
            FloatingActionButton(
                onClick = {
                    val newReminder = ReminderEntity(
                        id = UUID.randomUUID().toString(),
                        title = "Nuevo Recordatorio",
                        date = selectedDate.timeInMillis,
                        time = selectedTime.timeInMillis,
                        repeatInterval = "Diario"
                    )
                    runBlocking(Dispatchers.IO) {
                        reminderRepository.insert(newReminder)
                    }

                    selectedDate = Calendar.getInstance()
                    selectedTime = Calendar.getInstance()
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .background(colorResource(id = R.color.fondoBotones))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Recordatorio"
                )
            }
        }
    }


    if (isDatePickerDialogVisible) {
        DatePickerDialog(
            selectedDate,
            onDateSelected = { newDate ->
                selectedDate = newDate
                isDatePickerDialogVisible = false
            }
        ) {
            isDatePickerDialogVisible = false
        }
    }

    // Diálogo de selección de hora personalizado
    if (isTimePickerDialogVisible) {
        TimePickerDialog(
            selectedTime,
            onTimeSelected = { newTime ->
                selectedTime = newTime
                isTimePickerDialogVisible = false
            }
        ) {
            isTimePickerDialogVisible = false
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
    var isDatePickerVisible by remember { mutableStateOf(false) }
    var isTimePickerVisible by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = {
                    isDatePickerVisible = true
                },
                modifier = Modifier
                    .weight(1f) // Asigna un peso igual a cada botón
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.fondoBotones)
                )
            ) {
                Text(
                    text = "Fecha",
                    color = colorResource(id = R.color.textoBotones)
                )
            }

            Button(
                onClick = {
                    isTimePickerVisible = true
                },
                modifier = Modifier
                    .weight(1f) // Asigna un peso igual a cada botón
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.fondoBotones)
                )
            ) {
                Text(
                    text = "Hora",
                    color = colorResource(id = R.color.textoBotones),
                    fontSize = 12.sp
                )
            }
        }
    }

    if (isDatePickerVisible) {
        DatePickerDialog(selectedDate, onDateSelected) {
            isDatePickerVisible = false
        }
    }

    if (isTimePickerVisible) {
        TimePickerDialog(selectedTime, onTimeSelected) {
            isTimePickerVisible = false
        }
    }
}

@Composable
fun DatePickerDialog(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {

    }
}

@Composable
fun TimePickerDialog(
    selectedTime: Calendar,
    onTimeSelected: (Calendar) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {

    }
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
fun ReminderCard(reminder: ReminderEntity, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
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

fun createCalendarEvent(context: Context, title: String, dateInMillis: Long) {
    val calendar = Calendar.getInstance()
    val event = ContentValues()

    val calendarId = getCalendarId(
        context.contentResolver,
        "Calendario de Recordatorios"
    ) // Reemplaza con el nombre de tu calendario

    if (calendarId != null) {
        event.put(CalendarContract.Events.CALENDAR_ID, calendarId)
        event.put(CalendarContract.Events.TITLE, title)
        event.put(CalendarContract.Events.DESCRIPTION, "Descripción del Recordatorio")
        event.put(CalendarContract.Events.EVENT_TIMEZONE, calendar.timeZone.id)
        event.put(CalendarContract.Events.DTSTART, dateInMillis)
        event.put(CalendarContract.Events.DTEND, dateInMillis)

        // Insertar evento en el calendario
        context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, event)
    } else {

    }
}

@Preview
@Composable
fun RemindersScreenPreview() {
    RemindersScreenPreview()
}