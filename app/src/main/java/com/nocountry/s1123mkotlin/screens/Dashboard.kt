@file:OptIn(ExperimentalMaterial3Api::class)

import android.annotation.SuppressLint
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.type.Date
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocountry.s1123mkotlin.screens.Reminder
import com.nocuntry.s1123mkotlin.R

@Composable
fun Dashboard(
    profiles: List<UserProfile>,
    reminders: List<Reminder>,
    onProfileClick: (UserProfile) -> Unit,
    onAddReminderClick: () -> Unit
)

{
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Perfiles de Usuario",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(profiles) { profile ->
                ProfileCard(profile = profile)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Button(
            onClick = onAddReminderClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Agregar Perfil")
        }

        Text(
            text = "Recordatorios",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(reminders) { reminder ->
                ReminderCard(reminder = reminder)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        FloatingActionButton(
            onClick = { onAddReminderClick() }
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Recordatorio")
        }
    }
}

@Composable
fun ProfileCard(profile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Muestra la imagen del perfil de usuario
        Image(
            painter = painterResource(id = profile.imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.fondo))
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Muestra el nombre del perfil de usuario
        Text(
            text = profile.name,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}

@Composable
fun ReminderCard(reminder: Reminder) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Muestra el título del recordatorio
        Text(
            text = reminder.title,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Muestra la fecha y hora del recordatorio
        Text(
            text = "Fecha: ${reminder.date}, Hora: ${reminder.time}",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Muestra el intervalo de repetición del recordatorio
        Text(
            text = "Repetición: ${reminder.repeatInterval}",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}


@Composable
fun ReminderList(reminders: List<Reminder>) {
    Column {
        reminders.forEach { reminder ->
            Text(text = reminder.title)
            Text(text = "Fecha: ${reminder.date}, Hora: ${reminder.time}")
            Text(text = "Repetición: ${reminder.repeatInterval}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
