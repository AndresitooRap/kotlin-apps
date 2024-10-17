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
fun Page_Lacteos() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        Filas(collection = Datos_lacteos)
    }
}

private val Datos_lacteos = listOf(
    Quintuple(
        R.drawable.vaca,
        R.string.Leche_de_vaca,
        R.string.Leche_de_vaca_origen,
        R.drawable.leche,
        R.string.Leche_de_vaca_descripcion
    ),
    Quintuple(
        R.drawable.cabra,
        R.string.leche_de_cabra,
        R.string.Leche_cabra_origen,
        R.drawable.leche_cabra,
        R.string.leche_cabra_descripción
    ),
    Quintuple(
        R.drawable.logo,
        R.string.Leche_condensada,
        R.string.Leche_condensada_origen,
        R.drawable.leche_condensada,
        R.string.Leche_condensada_descripcion
    ),
    Quintuple(
        R.drawable.logo,
        R.string.mantequilla,
        R.string.Mantequilla_origen,
        R.drawable.mantequilla,
        R.string.mantequilla_descripcion,
    ),
    Quintuple(
        R.drawable.logo,
        R.string.queso,
        R.string.Queso_Origen,
        R.drawable.queso,
        R.string.queso_descripción,
    ),
    Quintuple(
        R.drawable.logo,
        R.string.yogurt,
        R.string.Yogurt_origen,
        R.drawable.yogurt,
        R.string.yogurt_descripcion,
    ),
    Quintuple(
        R.drawable.cabra,
        R.string.yogurt_de_cabra,
        R.string.yogurt_de_cabra_origen,
        R.drawable.yogurt_de_cabra,
        R.string.yogurt_de_cabra_descripcion,
    ),

    ).map { DrawableStringQuintuple(it.imgheader, it.title, it.subtitle, it.imgbody, it.textbody) }
