package com.nocountry.s1123mkotlin.screens

import android.content.Intent
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nocountry.s1123mkotlin.recordatorios.Recordatorio
import com.nocountry.s1123mkotlin.recordatorios.RecordatoriosViewModel
import com.nocuntry.s1123mkotlin.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordatoriosScreen(
    navController: NavController,
    viewModel: RecordatoriosViewModel
) {
    var recordatorios by remember { mutableStateOf(mutableListOf<Recordatorio>()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.Recordatorios))
    ) {
        TopAppBar(
            title = { androidx.compose.material3.Text("Recordatorios") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // FAB para agregar un nuevo recordatorio
        FloatingActionButton(
            onClick = {
                val newRecordatorio = openGoogleCalendarForReminder(context as ComponentActivity)
                if (newRecordatorio != null) {
                    viewModel.addRecordatorio(newRecordatorio)
                    recordatorios = viewModel.getRecordatorios() as MutableList<Recordatorio>
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomEnd)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Agregar Recordatorio"
            )
        }

        // Mostrar la lista de recordatorios
        // Mostrar la lista de recordatorios
        for (recordatorio in recordatorios) {
            RecordatorioItem(
                recordatorio = recordatorio,
                onRecordatorioClick = { clickedRecordatorio ->
                    navController.navigate("detalles_recordatorio/${clickedRecordatorio.titulo}")
                }
            )
        }
    }
}

@Composable
fun RecordatorioItem(
    recordatorio: Recordatorio,
    onRecordatorioClick: (Recordatorio) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRecordatorioClick(recordatorio) } // Llama a la función con el recordatorio cuando se hace clic
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = recordatorio.titulo,
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = recordatorio.description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

private fun openGoogleCalendarForReminder(activity: ComponentActivity): Recordatorio {
    val intent = Intent(Intent.ACTION_INSERT)
    intent.data = CalendarContract.Events.CONTENT_URI
    intent.putExtra(CalendarContract.Events.TITLE, "Tomar medicamento")
    intent.putExtra(CalendarContract.Events.DESCRIPTION, "Tomar medicamento recetado")
    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Domicilio")
    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())

    // Asegúrate de verificar si hay aplicaciones disponibles para manejar el intent.
    if (intent.resolveActivity(activity.packageManager) != null) {
        activity.startActivity(intent)
    }

    val fecha = System.currentTimeMillis()
        .toString()
    val nuevoRecordatorio = Recordatorio( "Tomar medicamento", "Domicilio", "Ubicación", fecha)

    return nuevoRecordatorio
}


data class CalendarEvent(
    val title: String,
    val dateInMillis: Long,
    val timeInMillis: Long
)

