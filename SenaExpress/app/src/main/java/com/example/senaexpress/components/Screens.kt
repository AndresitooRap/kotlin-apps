package com.example.senaexpress.components

sealed class Screens (val route: String){
    object SplashScreen:Screens("SplashScreen")
    object LoginScreen:Screens("LoginScreens")
    object HomeScreen:Screens("HomeScreen")
    object CategoryScreen: Screens("CategoryScreen")
    object InformationScreen: Screens("InformationScreen")
    object LocationScreen: Screens("LocationScreen")
}