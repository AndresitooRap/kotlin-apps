package com.example.dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dashboard.R
import com.example.dashboard.components.DrawableStringQuintuple
import com.example.dashboard.components.Filas
import com.example.dashboard.components.Quintuple

@Composable
fun Page_Flores() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        Filas(collection = DatosFlores)
    }
}

private val DatosFlores = listOf(
    Quintuple(
        R.drawable.colombia,
        R.string.Orquidea,
        R.string.Orquidea_Colombia,
        R.drawable.orquidea,
        R.string.Orquidea_Descripción
    ),
    Quintuple(
        R.drawable.china,
        R.string.Rosa,
        R.string.Rosa_China,
        R.drawable.rosa,
        R.string.Rosa_Descripcion
    ),
    Quintuple(
        R.drawable.peru,
        R.string.Astromelias,
        R.string.Astromelias_Perú,
        R.drawable.astromelias,
        R.string.Astromelia_Descripción
    ),
    Quintuple(
        R.drawable.usa_mexico,
        R.string.Girasol,
        R.string.Girasol_Usa_México,
        R.drawable.girasol,
        R.string.Girasol_Descripción
    ),

    ).map { DrawableStringQuintuple(it.imgheader, it.title, it.subtitle, it.imgbody, it.textbody) }
