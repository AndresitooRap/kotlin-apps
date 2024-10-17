package com.example.projecto_login.pages.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projecto_login.components.TiendaApp
import com.example.projecto_login.pages.splash.SplashScreen

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = pantallas.SplashScreen.route) {
        composable(pantallas.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(pantallas.TiendaApp.route) {
            TiendaApp()
        }

    }
}