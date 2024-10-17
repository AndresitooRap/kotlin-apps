package com.example.dashboard.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.R
import com.example.dashboard.components.CreateChannelNotification
import com.example.dashboard.components.notificacionExtensa
import com.example.dashboard.components.notificacionImage
import com.example.dashboard.components.notificacionProgramada
import com.example.dashboard.components.notificacionSencilla

@Composable
fun Page_Contenidos() {
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

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

        Image(painterResource(R.drawable.persona_pregunta), contentDescription = null)
        Text(
            text = "CON DUDAS",
            style = MaterialTheme.typography.h3.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier.padding(bottom = 1.dp)
        )
        Text(
            text = "Agenda tú cita virtual en Google meet, cuando tú cita este agendada, se enviara la notificación",
            style = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp),

            modifier = Modifier
                .padding(horizontal = 40.dp)
        )
        Spacer(modifier = Modifier.paddingFromBaseline(30.dp))
        Button(
            onClick = {
                notificacionProgramada(
                    context,
                )
            },
            modifier = modifier.padding(horizontal = 80.dp),
            shape = CircleShape,
            border = BorderStroke(width = 1.dp, color = Color.Black),
            contentPadding = PaddingValues(vertical = 15.dp)
        ) {
            Text(text = "Agendar Cita", style = TextStyle(fontWeight = FontWeight.ExtraBold))
        }
    }
}