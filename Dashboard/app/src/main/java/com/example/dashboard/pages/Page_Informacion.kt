package com.example.dashboard.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dashboard.R
import com.example.dashboard.components.CreateChannelNotification
import com.example.dashboard.components.notificacionExtensa
import com.example.dashboard.components.notificacionImage
import com.example.dashboard.components.notificacionProgramada
import com.example.dashboard.components.notificacionSencilla

@Composable
fun Page_Informacion() {
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

    val textShort: String = "Ejemplo de notificación sencilla con prioridad por omisión (default)"
    val textLong: String =
        "¡Saludos! Esta e suna prueba de notificaciones. Debe aparecer " +
                "un icono a la derecha y el texto puede tener una longitud de 200 caracteres. " +
                "El tamaño de la notificación puede colapsar y/o expandirse. " +
                "Gracias y hasta pronto"

    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda
    )

    //Funcion de creació propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    val modifier = Modifier
        .padding(18.dp)
        .fillMaxWidth()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(18.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Informacion de Notificaciones",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(bottom = 100.dp)
        )
        Button(
            onClick = {
                notificacionSencilla(
                    context,
                    idChannel,
                    idNotification,
                    "Notificación Sencilla",
                    textShort
                )
            },
            modifier = modifier
        ) {
            Text(text = "Notificaciones Sencillas")
        }
        Button(
            onClick = {
                notificacionExtensa(
                    context,
                    idChannel,
                    idNotification + 1,
                    "Notificación Extensa",
                    textLong,
                )
            },
            modifier = modifier
        ) {
            Text(text = "Notificacione Extensas")
        }
        Button(
            onClick = {
                notificacionImage(
                    context,
                    idChannel,
                    idNotification + 2,
                    "Notificación con Imágen",
                    textLong,
                    imagenBig
                )
            },
            modifier = modifier
        ) {
            Text(text = "Notificaciones Con Imágen")
        }
        Button(
            onClick = {
                notificacionProgramada(
                    context,
                )
            },
            modifier = modifier
        ) {
            Text(text = "Notificaciones Programadas")
        }
    }
}