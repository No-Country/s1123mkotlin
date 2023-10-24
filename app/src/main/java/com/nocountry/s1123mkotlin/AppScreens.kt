package com.nocountry.s1123mkotlin

sealed class AppScreens(val route: String) {
    object Welcome : AppScreens("Welcome")
    object Dashboard: AppScreens("dashboard")
    object recordatorios : AppScreens("RemindersScreen")
    object consultas : AppScreens("Consultas")
    object farmacias: AppScreens("Farmacias")

    object sintomas : AppScreens("Sintomas")
}

