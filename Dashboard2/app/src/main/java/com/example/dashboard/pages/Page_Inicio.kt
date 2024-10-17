package com.example.dashboard.pages

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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dashboard.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.compose.widgets.DisappearingScaleBar
import com.google.maps.android.compose.widgets.ScaleBar

//Puntos de Geolocaclización
val senaCBA = LatLng(4.69606, -74.21563)
val TiendaSena = LatLng(4.696385, -74.214835)
val UnidadLacteosCBA = LatLng(4.695038, -74.215446)
val UnidadAgricolaCBA = LatLng(4.694168, -74.218453)

@Composable
fun Page_Inicio() {
    Column {
        MapasScreen()
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
            .padding(bottom = 80.dp)
    ) {
        GoogleMapView(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            }
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
                        .background(MaterialTheme.colors.background)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}

) {
    val SenaCbaState = rememberMarkerState(position = senaCBA)
    val TiendaCbaState = rememberMarkerState(position = TiendaSena)
    val UnidadLacteosCBAState = rememberMarkerState(position = UnidadLacteosCBA)
    val UnidadAgricolaCBAState = rememberMarkerState(position = UnidadAgricolaCBA)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState,
            onMapLoaded = onMapLoaded
        ) {
            Marker(
                state = UnidadAgricolaCBAState,
                title = "Unidad de producción agricola CBA"
            )
            MarkerInfoWindowContent(
                state = UnidadLacteosCBAState,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            ) {
                Text(
                    "Unidad de producción de Lacteos CBA",
                    color = Color.Black
                )
            }
            MarkerInfoWindow(
                state = TiendaCbaState,
                icon = BitmapDescriptorFactory.fromResource(R.drawable.logo),
            ) {
                Box(
                    modifier = Modifier.background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(35.dp, 35.dp, 35.dp, 35.dp)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bg_tienda),
                            contentDescription = null,
                            //contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            "Tienda Sena CBA",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
            content()
        }
        ScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopEnd),
            cameraPositionState = cameraPositionState
        )
        DisappearingScaleBar(
            modifier = Modifier
                .padding(top = 5.dp, end = 15.dp)
                .align(Alignment.TopStart),
            cameraPositionState = cameraPositionState
        )
    }
}

@Preview
@Composable
fun MapsPreview() {
    MapasScreen()
}