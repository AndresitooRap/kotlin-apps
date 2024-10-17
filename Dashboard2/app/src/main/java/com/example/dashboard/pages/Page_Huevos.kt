package com.example.dashboard.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashboard.R
import com.example.dashboard.components.DrawableStringQuintuple
import com.example.dashboard.components.Filas
import com.example.dashboard.components.Quintuple

@Composable
fun Page_Huevos() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        Filas(collection = Datos_huevos)
    }
}

private val Datos_huevos = listOf(
    Quintuple(
        R.drawable.gallina,
        R.string.Huevo_gallina,
        R.string.Huevo_gallina_Origen,
        R.drawable.huevo_gallina,
        R.string.Huevo_gallina_descripción
    ),
    Quintuple(
        R.drawable.codorniz,
        R.string.Huevo_codorniz,
        R.string.Huevo_codorniz_Origen,
        R.drawable.huevo_codorniz,
        R.string.Huevo_codorniz_descripción
    ),

    ).map { DrawableStringQuintuple(it.imgheader, it.title, it.subtitle, it.imgbody, it.textbody) }
