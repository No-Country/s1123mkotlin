package com.nocuntry.s1123mkotlin

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.AppNavigation
import com.nocountry.s1123mkotlin.screens.ReminderRepository
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme
import com.nocuntry.medichild.viewmodel.IAViewModel
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var reminderRepository: ReminderRepository

    @Inject
    lateinit var viewModel: IAViewModel

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MediChildTheme {
                val navController = rememberNavController()

                AppNavigation(navController, reminderRepository, viewModel)
            }
        }
    }
}

