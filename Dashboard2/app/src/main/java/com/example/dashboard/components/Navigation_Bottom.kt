package com.example.dashboard.components

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dashboard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun BottomMenu(
    navController: NavHostController,
    menu_items_bar: List<Items_Bar>
) {
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
    ) {
        BottomNavigation(
            modifier = Modifier.padding(
                0.dp,
                0.dp,
                60.dp,
                0.dp
            )
        ) {
            val currentRouteBar = Current_Route(navController = navController)
            menu_items_bar.forEach { item ->
                BottomNavigationItem(
                    selected = currentRouteBar == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) }
                )
            }
        }
    }
}

@Composable
fun Fab(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

    val textshort: String =
        "Pedimos disculpas, aún no esta disponible, ¡Pronto lo estara!, Atentamente: Equipo SENA"


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

    FloatingActionButton(
        backgroundColor = Color.DarkGray,
        onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Proximamente Disponible!",
                    actionLabel = "Aceptar",
                    duration = SnackbarDuration.Indefinite
                )
            }
            notificacionImage(
                context,
                idChannel,
                idNotification + 2,
                "Error 502",
                textshort,
                imagenBig
            )
        },
    ) {
        Icon(
            imageVector = Icons.Filled.List,
            tint = Color.White,
            contentDescription = "Recompensas"
        )
    }
}