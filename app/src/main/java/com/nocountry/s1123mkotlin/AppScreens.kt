package com.nocountry.s1123mkotlin

sealed class AppScreens(val route: String) {
    object Home : AppScreens("home")
    object Login : AppScreens("login")
    object Register : AppScreens("register")
    object Dashboard: AppScreens("dashboard")
}

