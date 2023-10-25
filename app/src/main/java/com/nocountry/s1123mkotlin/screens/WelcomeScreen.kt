package com.nocountry.s1123mkotlin.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.AppScreens
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme
import com.nocuntry.s1123mkotlin.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(navController: NavController) {


    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.fondo))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.medichild),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 22.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                // Bienvenida
                Text(
                    text = "¡Bienvenidos!",
                    style = MaterialTheme.typography.h5.copy(color = Color.White),
                    modifier = Modifier.align(CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))
                //  Acerca de la App
                Text(
                    text = "MediChild te ayuda a gestionar los medicamentos de tus hijos de manera sencilla y efectiva. " +
                            "Registra, crea y configura tus recordatorios para mantener a tus hijos saludables. " +
                            "Confía en MediChild para cuidar a quienes más amas y descubre la paz de un cuidado de salud más sencillo.",
                    style = MaterialTheme.typography.h6.copy(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                )

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("dashboard")
                },
                shape= CircleShape,
                content = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Dashboard"
                    )
                },
                backgroundColor = colorResource(id = R.color.fondoBotones)
            )
        },
        isFloatingActionButtonDocked = true,
    )
}

