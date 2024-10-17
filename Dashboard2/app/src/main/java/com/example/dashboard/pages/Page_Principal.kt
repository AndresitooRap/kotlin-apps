package com.example.dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dashboard.R

@Composable
fun Page_Principal() {
    Columna_Cards()
}

@Composable
fun Card_Informacion(
    @StringRes title: Int,
    @DrawableRes img: Int,
    @StringRes textbody: Int,
    modifier: Modifier = Modifier.padding(horizontal = 15.dp),
    elevation: Dp = 10.dp,

    background: Color = MaterialTheme.colors.surface,

    ) {
    Card(
        backgroundColor = background,
        shape = RoundedCornerShape(14.dp),
        elevation = elevation,
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        // Contenedor
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(Modifier.fillMaxWidth()) {

                    Text(
                        stringResource(title),
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold)
                    )

                }
            }

            // Multimedia
            Image(
                painterResource(img),
                contentDescription = "Multimedia de tarjeta",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(194.dp)
                    .clip(RectangleShape)
            )

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {

                // Texto de ayuda
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(textbody),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                    )

                }
            }

            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

@Composable
fun Columna_Cards() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier.padding(bottom = 90.dp)
    ) {
        items(DatosInfo) { item ->
            Card_Informacion(item.title, item.img, item.textbody)
        }
    }
}

private val DatosInfo = listOf(
    Triple(
        R.string.Campesena,
        R.drawable.campesena,
        R.string.Campesena_Info
    ),
    Triple(
        R.string.Sennova,
        R.drawable.sennova,
        R.string.Sennova_info
    ),
    Triple(
        R.string.inscripcion,
        R.drawable.inscripcion,
        R.string.inscripción_info
    ),


    ).map { DrawableStringTriple(it.title, it.img, it.textbody) }


data class Triple(
    @StringRes val title: Int,
    @DrawableRes val img: Int,
    @StringRes val textbody: Int

)

data class DrawableStringTriple(
    val title: Int,
    val img: Int,
    val textbody: Int
)

@Preview
@Composable
fun Card_Preview() {
    Columna_Cards(

    )
}
