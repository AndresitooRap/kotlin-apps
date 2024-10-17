package com.example.projecto_login.pages.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projecto_login.R
import com.example.projecto_login.pages.home.pantallas
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(pantallas.TiendaApp.route)
    }
    Splash()
}

@Composable
fun Splash() {
    //IMAGEN
    val transition = rememberInfiniteTransition()

    val angle by transition.animateFloat(
        initialValue = -45f,
        targetValue = 45f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val translation by transition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    //TEXTO
    val isVisible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1500) // Retraso de 2 segundos
        isVisible.value = true
    }
    val alpha by animateFloatAsState(
        targetValue = if (isVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 300) // Duración de la animación de desvanecimiento
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    rotationZ = angle
                    translationX = translationX
                }
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (isVisible.value) {
            Text(
                text = "Login SENA",
                style = TextStyle(fontSize = 40.sp),
                modifier = Modifier.alpha(alpha)
            )
        } else {
            Text(
                text = "Login SENA",
                style = TextStyle(color = Color.Transparent, fontSize = 40.sp)
            )

        }
    }
}

fun isSwinging(): Boolean {
    val currentTimeMillis = System.currentTimeMillis()
    val swingDurationMillis = 3000
    val elapsedTimeMillis = currentTimeMillis % swingDurationMillis
    return elapsedTimeMillis < (swingDurationMillis / 2)
}