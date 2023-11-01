package com.nocuntry.s1123mkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.nocountry.s1123mkotlin.AppNavigation
import com.nocountry.s1123mkotlin.sintomas.SymptomNotesViewModel
import com.nocountry.s1123mkotlin.ui.theme.MediChildTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val symptomNotesViewModel: SymptomNotesViewModel by viewModels()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MediChildTheme {
                val navController = rememberNavController()

                AppNavigation(navController, symptomNotesViewModel)

            }
        }
    }
}
