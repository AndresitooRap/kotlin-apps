package com.example.dashboard.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.dashboard.components.notificacionSencilla

@Composable
fun Page_Ver_Mas() {

    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

    val textShort: String = "¡Tú turno ha sido Agendado!"


    //Funcion de creació propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    Column(modifier = Modifier) {
        Box(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.DarkGray)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Column() {
                    Image(
                        painter = painterResource(R.drawable.logo_blanco),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                    )
                }
                Spacer(modifier = Modifier.width(60.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Bienvenido a",
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Text(
                        "la Tienda Sena",
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }

        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Row() {
            Spacer(modifier = Modifier.padding(start = 40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(right = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = MaterialTheme.colors.primary)
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Row(
                        modifier = Modifier.absolutePadding(
                            left = 20.dp,
                            top = 10.dp,
                            right = 20.dp,
                            bottom = 3.dp
                        ), horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            "¿Quiénes Somos?",
                            style = MaterialTheme.typography.h5.copy(
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,

                                ),
                        )
                    }
                    Row(modifier = Modifier.absolutePadding(left = 20.dp, bottom = 10.dp)) {
                        Text(
                            "Ofrecemos formación gratuita a millones de colombianos que se benefician con programas técnicos, tecnológicos y complementarios que enfocados en el desarrollo económico, científico y social del país.",
                            style = MaterialTheme.typography.h6.copy(
                                color = Color.White,
                                fontSize = 18.sp
                            ),
                            letterSpacing = 0.9.sp,
                        )
                    }
                }

            }
        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))



        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Row() {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(left = 10.dp, right = 60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = MaterialTheme.colors.primary)
            ) {
                Column() {
                    Row(
                        modifier = Modifier.absolutePadding(
                            left = 20.dp,
                            top = 10.dp,
                            bottom = 3.dp
                        )
                    ) {
                        Text(
                            "¿Cómo Comprar?",
                            style = MaterialTheme.typography.h5.copy(
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                    Row(modifier = Modifier.absolutePadding(left = 20.dp, bottom = 10.dp)) {
                        Text(
                            "Para la compra de nuestros productos, tienes que ir a nuestro punto físico Km 7 Mosquera-Bogotá entre Las 08:00 hasta las 17:00",
                            style = MaterialTheme.typography.h6.copy(
                                color = Color.White,
                                fontSize = 18.sp
                            ), letterSpacing = 0.9.sp
                        )
                    }
                }

            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    "Agenda Tu Turno aquí 👇",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold),
                    letterSpacing = 1.sp
                )
                var click by rememberSaveable { mutableStateOf(true) }
                OutlinedButton(onClick = {
                    click = false
                    notificacionSencilla(
                        context,
                        idChannel,
                        idNotification,
                        "Turno Agendado",
                        textShort
                    )

                }, enabled = click, modifier = Modifier.absolutePadding(left = 160.dp)) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Click Me")
                }
            }

        }
    }

}