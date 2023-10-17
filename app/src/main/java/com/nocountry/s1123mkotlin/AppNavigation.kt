package com.nocountry.s1123mkotlin

import Dashboard
import RegisterScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.login.LoginScreen
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocountry.s1123mkotlin.screens.Main

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.Home.route) {

        composable(route = AppScreens.Home.route) {
            Main(navController= navController)
        }


        // Login
        composable(route = AppScreens.Login.route) {
            LoginScreen(navController)
        }

        // Register
        composable(route = AppScreens.Register.route) {
            RegisterScreen(navController)
        }

        // Dashboard
        composable(route = AppScreens.Dashboard.route) {
            Dashboard(
                profiles = emptyList(), // Lista de perfiles de usuario (ajusta esta lista)
                reminders = emptyList(), // Lista de recordatorios (ajusta esta lista)
                onProfileClick = { profile: UserProfile ->
                    // Lógica a ejecutar cuando se hace clic en un perfil
                },
                onAddReminderClick = {
                    // Lógica para agregar un nuevo recordatorio
                }
            )
        }
    }
}

