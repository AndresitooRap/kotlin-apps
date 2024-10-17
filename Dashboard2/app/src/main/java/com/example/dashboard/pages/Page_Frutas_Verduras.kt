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
fun Page_Frutas_Verduras() {
    Column(modifier = Modifier.padding(bottom = 70.dp)) {
        Filas(collection = DatosFrutas_Verduras)
    }
}

private val DatosFrutas_Verduras = listOf(
    Quintuple(
        R.drawable.kazajistan,
        R.string.Manzana,
        R.string.Manzana_Kazajistán,
        R.drawable.manzana,
        R.string.Manzana_Descripción
    ),
    Quintuple(
        R.drawable.usa,
        R.string.Mora_Azul,
        R.string.Mora_Azul_USA,
        R.drawable.mora_azul,
        R.string.Mora_Azul_Descripción
    ),
    Quintuple(
        R.drawable.indonesia,
        R.string.Naranja,
        R.string.Naranaja_indonesia,
        R.drawable.mandarina,
        R.string.Naranja_Descripción
    ),
    Quintuple(
        R.drawable.indomalaya,
        R.string.Banano,
        R.string.Banano_indomalaya,
        R.drawable.banano,
        R.string.Banano_Descripción,
    ),
    Quintuple(
        R.drawable.armenia,
        R.string.Pera,
        R.string.Pera_Armenia,
        R.drawable.pera,
        R.string.Pera_Descripcion,
    ),
    Quintuple(
        R.drawable.nueva_guinea,
        R.string.Remolacha,
        R.string.Remolacha_Nueva_Guinea,
        R.drawable.remolacha,
        R.string.Remolacha_Descripción,
    ),

    ).map { DrawableStringQuintuple(it.imgheader, it.title, it.subtitle, it.imgbody, it.textbody) }
