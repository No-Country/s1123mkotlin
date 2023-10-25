package com.nocountry.s1123mkotlin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.screens.ReminderRepository
import com.nocountry.s1123mkotlin.screens.Dashboard
import com.nocountry.s1123mkotlin.screens.RemindersScreen
import com.nocountry.s1123mkotlin.screens.SintomasScreen
import com.nocountry.s1123mkotlin.screens.WelcomeScreen
import com.nocuntry.medichild.viewmodel.IAViewModel

@RequiresApi(Build.VERSION_CODES.O)


@Composable
fun AppNavigation(navController: NavHostController,
                  reminderRepository: ReminderRepository,
                  viewModel: IAViewModel) {



    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }


        composable(route = AppScreens.Dashboard.route) {
            Dashboard(
                navController = navController,
                onConsultationsClick = {},
                onPharmacyClick = {},
                onRemindersClick = {},
                onSymptomsClick = {},
                onUserProfileClick = {}
            )
        }

        composable(route = AppScreens.recordatorios.route) {
            RemindersScreen(
                navController = navController,
                reminderRepository = reminderRepository
            )
        }

        composable(route= AppScreens.consultas.route){

        }

        composable(route = AppScreens.sintomas.route) {
            SintomasScreen(
                viewModel = viewModel,
                reminderRepository = reminderRepository
            )
        }



        composable(route= AppScreens.farmacias.route){

        }
    }
}


