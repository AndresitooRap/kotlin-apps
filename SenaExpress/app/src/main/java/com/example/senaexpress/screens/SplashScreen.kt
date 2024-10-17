package com.example.senaexpress.screens

import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.senaexpress.R
import com.example.senaexpress.components.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    //Efecto para el Splash
    LaunchedEffect(Unit) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(Screens.LoginScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {

    //Valor para los circulos y el movimiento
    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    //Valor de los colores que se muestran
    val colors = listOf(
        Color(0xFF38F413),
        Color(0xFF27D29F),
        Color(0xFF91E426),
        Color(0xFF219419),
    )
    //Valor para poder unirlos
    var gradientBrush by remember {
        mutableStateOf(
            Brush.horizontalGradient(
                colors = colors,
                startX = -10.0f,
                endX = 400.0f,
                tileMode = TileMode.Repeated
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Valor para saber si esta visible, el cual inicia en falso
        val isVisible = remember { mutableStateOf(false) }

        //Efecto para el texto
        LaunchedEffect(Unit) {
            delay(1500) // Retraso de 2 segundos
            isVisible.value = true
        }
        val alpha by animateFloatAsState(
            targetValue = if (isVisible.value) 1f else 0f,
            animationSpec = tween(durationMillis = 300) // Duración de la animación de desvanecimiento
        )

        //Si es visible se muestra
        if (isVisible.value) {
            Text(
                stringResource(id = R.string.Welcome),
                style = TextStyle(fontSize = 40.sp),
                modifier = Modifier
                    .alpha(alpha)
                    .padding(5.dp)
            )
        } else {
            //Si no es visible, aún se muestra pero en color transparente, es decir el color nulo
            Text(
                stringResource(id = R.string.Welcome),
                style = TextStyle(color = Color.Transparent, fontSize = 40.sp),
                modifier = Modifier.padding(5.dp)
            )

        }
        //La caja para poder poner el circulo que se mueve con la gradiante y la imagen
        Box(modifier = Modifier
            .drawBehind {
                rotate(value) {
                    drawCircle(
                        gradientBrush, style = Stroke(width = 20.dp.value)
                    )
                }
            }
            .size(260.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp)
                    .padding(bottom = 10.dp)
            )

        }
    }

}