package com.example.dashboard.components

import com.example.dashboard.R

sealed class Items_Bar(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object  Boton1: Items_Bar(R.drawable.ic_bike, "Inicio", "boton1")
    object  Boton2: Items_Bar(R.drawable.ic_moto, "Contenidos", "boton2")
    object  Boton3: Items_Bar(R.drawable.ic_recycling, "Información", "boton3")
}