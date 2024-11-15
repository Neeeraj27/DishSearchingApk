package com.example.stocktrackingapk.utils

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object SplashScreen : Screen("SplashScreen")


}
