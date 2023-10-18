package com.nocountry.s1123mkotlin.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocuntry.s1123mkotlin.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    navController: NavController,
    profiles: List<UserProfile>
) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dashboard") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(colorResource(id = R.color.fondo))
                .wrapContentWidth(align = Alignment.CenterHorizontally)
        ) {
            UserProfileAvatar(imageResId = profiles[0].imageResId) // Muestra el avatar del primer usuario

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(profiles) { profile ->
                    UserProfileCard(profile = profile, navController)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Carta de "Recordatorios"
                DashboardCard(
                    title = "Recordatorios",
                    imageResId = R.drawable.recordatorio,
                    onClick = { navController.navigate("reminders") }
                )

                // Carta de "Consultas"
                DashboardCard(
                    title = "Consultas",
                    imageResId = R.drawable.consultas,
                    onClick = { navController.navigate("consultations") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Carta de "Farmacias"
                DashboardCard(
                    title = "Farmacias",
                    imageResId = R.drawable.farmacia,
                    onClick = { navController.navigate("pharmacies") }
                )

                // Carta de "Síntomas"
                DashboardCard(
                    title = "Síntomas",
                    imageResId = R.drawable.sintomas,
                    onClick = { navController.navigate("symptoms") }
                )
            }
        }
    }
}

@Composable
fun UserProfileAvatar(imageResId: Int) {
    // avatar del perfil del usuario
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    )
}

@Composable
fun UserProfileCard(profile: UserProfile, navController: NavController) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable { navController.navigate("profile/${profile.profileId}") }
            .padding(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mostrar la imagen del perfil
            Image(
                painter = painterResource(id = profile.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Mostrar el nombre del perfil
            Text(
                text = profile.name,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun DashboardCard(title: String, imageResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .clickable(onClick = onClick)
            .padding(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
