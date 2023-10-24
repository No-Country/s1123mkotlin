package com.nocuntry.s1123mkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.AppNavigation
import com.nocountry.s1123mkotlin.screens.ReminderRepository
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var reminderRepository: ReminderRepository

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MediChildTheme {
                val navController = rememberNavController()

                AppNavigation(navController, reminderRepository)
            }
        }
    }
}
