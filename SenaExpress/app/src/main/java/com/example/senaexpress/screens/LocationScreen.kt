package com.example.senaexpress.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.senaexpress.R
import com.google.android.gms.maps.StreetViewPanoramaOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.streetview.StreetView
import com.google.maps.android.compose.streetview.rememberStreetViewCameraPositionState


//Puntos de Geolocaclización
val senaCBA = LatLng(4.69606, -74.21563)
val TiendaSena = LatLng(4.696385, -74.214835)

@Composable
fun LocationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            MapasScreen()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            StreetViewSena()
        }

    }
}


@Composable
fun MapasScreen() {
    val defaultCameraPosition = CameraPosition.fromLatLngZoom(senaCBA, 15f)
    //Objeto para pasar al mapa
    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }
    //Variable para determinar si mapa se cargo
    var isMapLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMapView(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            },

            )
        if (!isMapLoaded) {
            AnimatedVisibility(
                modifier = Modifier.matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Composable
fun StreetViewSena() {
    //Valor para saber si se pueve mover en 360 grados
    val isPanningEnabled by remember {
        mutableStateOf(true)
    }
    //Valor para poder poner los nombres de la calle
    val isStreetNamesEnabled by remember {
        mutableStateOf(
            true
        )
    }
    //Valor para poder navegar, es decir, adelantar y atrasar
    val isUserNavigationEnabled by remember {
        mutableStateOf(true)
    }
    //Valor para poder hacer zoom
    val isZoomGestureEnabled by remember {
        mutableStateOf(true)
    }
    //Valor para poder recordar la cámara
    val camera = rememberStreetViewCameraPositionState()

    Box(
        modifier = Modifier
            .fillMaxSize(), Alignment.BottomCenter
    ) {
        StreetView(
            Modifier.matchParentSize(),
            cameraPositionState = camera,
            streetViewPanoramaOptionsFactory = {
                StreetViewPanoramaOptions().position(TiendaSena)
            },
            isPanningGesturesEnabled = isPanningEnabled,
            isStreetNamesEnabled = isStreetNamesEnabled,
            isUserNavigationEnabled = isUserNavigationEnabled,
            isZoomGesturesEnabled = isZoomGestureEnabled,

            )
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}

) {
    val SENA = rememberMarkerState(position = TiendaSena)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState,
            onMapLoaded = onMapLoaded,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {

            MarkerInfoWindow(
                state = SENA,
            ) {
                Box(
                    modifier = Modifier.background(
                        color = Color(0xFF2FAA16),

                        shape = RoundedCornerShape(40.dp)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_sena),
                            contentDescription = null,
                            modifier = Modifier
                                .height(150.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = stringResource(id = R.string.Tienda_Sena_CBA),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),

                            )
                    }
                }
            }
            content()
        }
    }
}