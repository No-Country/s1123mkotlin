package com.nocountry.s1123mkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nocountry.s1123mkotlin.AppScreens
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme
import com.nocuntry.s1123mkotlin.R

@Composable
fun Main(navController: NavController) {

    MediChildTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Iniciar Sesión con Google")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            Button(
                onClick = {
                    navController.navigate(AppScreens.Register.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrarse")
            }

            Spacer(modifier = Modifier.height(24.dp))


            // Enlace pantalla de registro
            Text(
                text = "¿No tienes cuenta? Créala aquí",
                color = Color.Blue,
                modifier = Modifier
                    .clickable {
                    navController.navigate(AppScreens.Register.route)
                }
            )
        }
    }
}
