package com.example.projecto_login.pages.home

sealed class pantallas(val route: String) {
    object SplashScreen : pantallas("splash_screen")
    object TiendaApp : pantallas("pantalla_principal")
}


