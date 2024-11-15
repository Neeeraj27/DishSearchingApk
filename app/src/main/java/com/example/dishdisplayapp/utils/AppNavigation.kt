package com.example.dishdisplayapp.utils



import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dishdisplayapp.SplashScreen
import com.example.dishdisplayapp._Ui.Screens.MainScreen
import com.example.dishdisplayapp.viewmodel.DishViewModel
import com.example.stocktrackingapk.utils.Screen

@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("RememberReturnType", "SuspiciousIndentation")
@Composable
fun AppNavigation(
    navController: NavHostController,
    dishViewModel: DishViewModel,activity: Activity

)
{

    NavHost(navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route)
        {
            SplashScreen(navController = navController,activity)
        }

     composable(Screen.MainScreen.route)
        {
           MainScreen(dishViewModel)
        }

    }
}