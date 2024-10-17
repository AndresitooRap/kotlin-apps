package com.example.senaexpress.components

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainPage(videoUri: Uri) {
    val navController = rememberNavController()

    val scaffoldScreens = listOf(
        Screens.HomeScreen.route,
        Screens.CategoryScreen.route,
        Screens.InformationScreen.route,
        Screens.LocationScreen.route
    )

    val currentRoute = currentRoute(navController)

    if (currentRoute in scaffoldScreens) {
        Scaffold(
            topBar = { AppBar() },
            floatingActionButton = { FAB() },
            bottomBar = { BottomNavBar(navController) },
        ) { padding ->
            ContentScaffold(padding = padding)
            Navegation(videoUri, navController)
        }
    } else {
        Navegation(videoUri, navController)
    }


}

@Composable
fun ContentScaffold(padding: PaddingValues) {

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

