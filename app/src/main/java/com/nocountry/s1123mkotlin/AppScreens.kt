package com.nocountry.s1123mkotlin

sealed class AppScreens(val route: String) {
    object Welcome : AppScreens("welcome")
    object Dashboard : AppScreens("dashboard")
    object Reminders : AppScreens("recordatorios")
    object Consultas : AppScreens("consultas")
    object Farmacias : AppScreens("farmacias")
    object Sintomas : AppScreens("sintomas")
}

