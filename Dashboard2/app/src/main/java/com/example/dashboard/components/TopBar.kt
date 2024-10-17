package com.example.dashboard.components

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dashboard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menuItem: List<MenuItem>
) {

    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

    val textLong: String =
        "Nos acabas de Darnos un like " +
                "Esto nos ayuda para la popularidad de nuestra app " +
                "Esperamos que te haya gustado la app y que la sigas usando " +
                "Atentamente, SENA CBA"
    val bigIcono = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.logo
    )

    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    var showMenu by remember {
        mutableStateOf(false)
    }
    var currentRoute = Current_Route(navController = navController)
    var myTytle = "Tienda Sena CBA"
    menuItem.forEach { item ->
        if (currentRoute == item.ruta) myTytle = item.title
    }
    TopAppBar(
        title = { Text(text = myTytle) },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Icono de Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refrescar"
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Configurar"
                )
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Mas Opciones"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.width(150.dp)
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Idioma"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Idioma")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    IconButton(onClick = {
                        notificacionExtensa(
                            context,
                            idChannel,
                            idNotification + 1,
                            "¡Gracias Usuario! ",
                            textLong, bigIcono,
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Compartir"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Like")
                }
            }
        }
    )
}