package com.example.senaexpress.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Public
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.senaexpress.R

sealed class Items_NavBar(
    val route: String,
    val title: Int,
    val icon: ImageVector,
    val icon_focused: ImageVector
) {

    object Home :
        Items_NavBar(
            Screens.HomeScreen.route,
            R.string.Home,
            Icons.Outlined.Home,
            Icons.Filled.Home
        )

    object Category :
        Items_NavBar(
            Screens.CategoryScreen.route,
            R.string.Category,
            Icons.Outlined.Category,
            Icons.Filled.Category
        )

    object Information :
        Items_NavBar(
            Screens.InformationScreen.route,
            R.string.News,
            Icons.Outlined.Info,
            Icons.Filled.Info
        )

    object Location :
        Items_NavBar(
            Screens.LocationScreen.route,
            R.string.Location,
            Icons.Outlined.Public,
            Icons.Filled.Public
        )

}