package com.nocountry.s1123mkotlin


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.login.LoginScreen
import com.nocountry.s1123mkotlin.login.RegisterScreen
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocountry.s1123mkotlin.screens.DashboardScreen
import com.nocountry.s1123mkotlin.screens.Main
import com.nocuntry.s1123mkotlin.R

@RequiresApi(Build.VERSION_CODES.O)


@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AppScreens.Home.route) {

        composable(route = AppScreens.Home.route) {
            Main(navController = navController)
        }

        composable(route = AppScreens.Login.route) {
            LoginScreen(navController = navController) {

            }
        }

        composable(route = AppScreens.Register.route) {
            RegisterScreen(navController)
        }

        composable(route = AppScreens.Dashboard.route) {

            DashboardScreen(
                navController = navController,
                emptyList()
            )
        }
    }
}


