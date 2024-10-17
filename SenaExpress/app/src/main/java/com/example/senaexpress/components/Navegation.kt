package com.example.senaexpress.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.senaexpress.screens.CategoryScreen
import com.example.senaexpress.screens.HomeScreen
import com.example.senaexpress.screens.InformationScreen
import com.example.senaexpress.screens.LocationScreen
import com.example.senaexpress.screens.LoginScreen
import com.example.senaexpress.screens.SplashScreen

@Composable
fun Navegation(videoUri: Uri, navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(videoUri, navController)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screens.CategoryScreen.route) {
            CategoryScreen()
        }
        composable(Screens.InformationScreen.route) {
            InformationScreen()
        }
        composable(Screens.LocationScreen.route) {
            LocationScreen()
        }
    }

}