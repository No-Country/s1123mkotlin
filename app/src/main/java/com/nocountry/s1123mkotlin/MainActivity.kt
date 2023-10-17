package com.nocuntry.s1123mkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.AppNavigation
import com.nocountry.s1123mkotlin.login.LoginScreen
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MediChildTheme {
                // Create a navigation controller
                val navController = rememberNavController()


                AppNavigation(navController)
            }
        }
    }
}
