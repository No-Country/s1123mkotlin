package com.nocountry.s1123mkotlin


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nocountry.s1123mkotlin.consultas.ConsultasScreenViewModel
import com.nocountry.s1123mkotlin.recordatorios.DetallesRecordatorioScreen
import com.nocountry.s1123mkotlin.recordatorios.RecordatoriosViewModel
import com.nocountry.s1123mkotlin.screens.ConsultasScreen
import com.nocountry.s1123mkotlin.screens.Dashboard
import com.nocountry.s1123mkotlin.screens.RecordatoriosScreen
import com.nocountry.s1123mkotlin.screens.SintomasScreen
import com.nocountry.s1123mkotlin.screens.WelcomeScreen
import com.nocountry.s1123mkotlin.sintomas.AddSymptomNoteScreen
import com.nocountry.s1123mkotlin.sintomas.SymptomNotesViewModel


@ExperimentalMaterial3Api
@RequiresApi(Build.VERSION_CODES.O)


@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: SymptomNotesViewModel
) {
    val consultasViewModel = viewModel<ConsultasScreenViewModel>()
    val recordatoriosViewModel = viewModel<RecordatoriosViewModel>()



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

        composable(route = AppScreens.Reminders.route) {
            RecordatoriosScreen(
                navController = navController,
                viewModel = viewModel()
            )
        }

        composable(route= AppScreens.Consultas.route){
            ConsultasScreen(
                navController = navController,
                viewModel = consultasViewModel
            )
        }
        composable("detalles_recordatorios/{titulo}") { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo")
            val recordatorio = recordatoriosViewModel.findRecordatorioByTitulo(titulo.toString())

            if (recordatorio != null) {
                // Renderizar la pantalla de detalles del recordatorio con el objeto recordatorio
                DetallesRecordatorioScreen(recordatorio = recordatorio, navController= navController)
            } else {
                // Manejar el caso en el que no se encuentra el recordatorio
            }
        }


        composable(route= AppScreens.Farmacias.route){

        }

        composable(route = AppScreens.Sintomas.route) {
            SintomasScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable("add_sintoma") {
           AddSymptomNoteScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

    }
}









