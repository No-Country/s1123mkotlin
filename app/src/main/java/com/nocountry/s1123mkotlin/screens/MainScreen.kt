package com.nocountry.s1123mkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nocountry.s1123mkotlin.AppScreens
import com.nocuntry.s1123mkotlin.R

@Composable
fun Main(navController: NavController) {

    val fondo = Color(0xFF4D88BC) // Color azul
    val fondoBotones = Color(0xFFF0C8CB) // Color rosa o rojo claro
    val textoBotones = Color(0xFF283BB5) // Color azul
    val titulos = Color(0xFFFDFEFF) // Color blanco o marfil

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.medichild),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de inicio de sesión con Google
        Button(
            onClick = {
                navController.navigate(AppScreens.Login.route)
            },

            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(22.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.fondoBotones))


        ) {
            Text(
                text = "Iniciar Sesión con Google",
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de registro
        Button(
            onClick = {
                navController.navigate(AppScreens.Register.route)
            },

            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 26.dp),
            shape = RoundedCornerShape(22.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.fondoBotones))

        ) {
            Text(
                text = "Registrarse",
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        // Enlace pantalla de registro
        Text(
            text = "¿No tienes cuenta? Créala aquí",
            color = Color.White,
            modifier = Modifier
                .clickable {
                    navController.navigate(AppScreens.Register.route)
                }
        )
    }
}




