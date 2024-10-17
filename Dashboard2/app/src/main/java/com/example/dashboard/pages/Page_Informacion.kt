package com.example.dashboard.pages

import android.annotation.SuppressLint
import android.graphics.drawable.ShapeDrawable
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dashboard.R

@Composable
fun Page_Informacion() {
    Greetings()
}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(6) { "$it" }
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp),
        contentPadding = PaddingValues(bottom = 90.dp, top = 20.dp)
    ) {
        items(Datos) { item ->
            Greeting(item.title, item.name)
        }
    }
}

@Composable
private fun Greeting(@StringRes name: Int, @StringRes title: Int) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 8.dp,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.padding(
            vertical = 4.dp, horizontal = 8.dp
        )
    ) {
        CardContent(title, name)
    }
}

@Composable
private fun CardContent(@StringRes title: Int, @StringRes name: Int) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card() {

    }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                stringResource(name),
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(vertical = 5.dp)
            )

            if (expanded) {
                Text(

                    stringResource(title)
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ArrowBack else Icons.Filled.ArrowDropDown,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

private val Datos = listOf(
    R.string.Como_puedo_comprar to R.string.Respuesta1,
    R.string.Son_Naturales to R.string.Respuesta2,
    R.string.Cómo_pesan to R.string.Respuesta3,
    R.string.Precio to R.string.Respuesta4,
    R.string.Huevos to R.string.Respuesta5,
    R.string.Flores to R.string.Respuesta6,
    R.string.Lacteos to R.string.Respuesta7
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @StringRes val title: Int, @StringRes val name: Int
)

