package com.example.dashboard.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dashboard.R
import com.example.dashboard.ui.theme.Shapes
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Cardsita(
    @DrawableRes imgheader: Int,
    @StringRes title: Int,
    @StringRes subtitle: Int,
    @DrawableRes imgbody: Int,
    @StringRes textbody: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),

        border = BorderStroke(width = 1.dp, color = Color.Gray)
    ) {
        Column() {
            //Header
            Row(
                Modifier
                    .height(72.dp)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.Transparent,
                            shape = CircleShape,
                        )
                        .size(46.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(imgheader),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(Modifier.fillMaxWidth()) {
                    Text(stringResource(title), style = MaterialTheme.typography.h6, maxLines = 1)
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            stringResource(subtitle),
                            style = MaterialTheme.typography.body1,
                            maxLines = 1
                        )
                    }

                }

            }
            //Cuerpo
            Image(
                painter = painterResource(imgbody),
                contentDescription = "Multimedia de tarjeta",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(194.dp)
                    .clip(RectangleShape)
            )
            Row(Modifier.padding(start = 16.dp, end = 20.dp, top = 16.dp)) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(textbody),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            //Pie
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.medium,
                LocalMinimumTouchTargetEnforcement provides false
            ) {

                Row() {

                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Row() {
                            var dialogOpen by remember {
                                mutableStateOf(false)
                            }
                            if (dialogOpen) {
                                AlertDialog(
                                    onDismissRequest = {
                                        // Dismiss the dialog when the user clicks outside the dialog or on the back button.
                                        // If you want to disable that functionality, simply leave this block empty.
                                        dialogOpen = false
                                    },
                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                // perform the confirm action and
                                                // close the dialog
                                                dialogOpen = false
                                            }
                                        ) {
                                            Text(text = "Volver")
                                        }
                                    },

                                    title = {
                                        Text(
                                            stringResource(title),
                                            style = TextStyle(
                                                color = MaterialTheme.colors.primary,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 20.sp
                                            )
                                        )
                                    },
                                    text = {
                                        Text(
                                            stringResource(textbody),
                                            letterSpacing = 1.sp,
                                            style = TextStyle(fontWeight = FontWeight.Bold),
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(32.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    backgroundColor = Color.White
                                )
                            }
                            TextButton(onClick = {
                            }
                            ) {
                                Text(text = "Comprar")
                            }

                            TextButton(onClick = { dialogOpen = true }) {
                                Text(text = "Detalle")
                            }
                        }
                    }
                    Column(modifier = Modifier.absolutePadding(right = 2.dp, bottom = 1.dp)) {
                        var colorrandom by remember { mutableStateOf(Color.Gray) }

                        IconButton(onClick = {
                            val randomColor =
                                Color(Random.nextLong(0xFF000000, 0xFFFFFFFF))
                            colorrandom = randomColor

                        }) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                tint = colorrandom,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(30.dp)) {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                    }

                }


            }
        }
    }
}


@Composable
fun Filas(collection: List<DrawableStringQuintuple>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(
            collection
        ) { item ->
            Cardsita(
                item.imgheader,
                item.title,
                item.subtitle,
                item.imgbody,
                item.textbody,
            )
        }
    }
}

private val DatosFilas = listOf(
    Quintuple(
        R.drawable.ic_flores,
        R.string.CanalTienda,
        R.string.CanalTienda,
        R.drawable.ic_frutas_verduras,
        R.string.CanalTienda
    ),
    Quintuple(
        R.drawable.ic_flores,
        R.string.CanalTienda,
        R.string.CanalTienda,
        R.drawable.ic_frutas_verduras,
        R.string.CanalTienda
    ),
    Quintuple(
        R.drawable.ic_flores,
        R.string.CanalTienda,
        R.string.CanalTienda,
        R.drawable.ic_frutas_verduras,
        R.string.CanalTienda
    ),
    Quintuple(
        R.drawable.ic_flores,
        R.string.CanalTienda,
        R.string.CanalTienda,
        R.drawable.ic_frutas_verduras,
        R.string.CanalTienda
    ),

    ).map { DrawableStringQuintuple(it.imgheader, it.title, it.subtitle, it.imgbody, it.textbody) }


data class Quintuple(
    @DrawableRes val imgheader: Int,
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val imgbody: Int,
    @StringRes val textbody: Int

)

data class DrawableStringQuintuple(
    val imgheader: Int,
    val title: Int,
    val subtitle: Int,
    val imgbody: Int,
    val textbody: Int
)


/*@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MaterialTheme {
        Cardsita(img = R.drawable.ic_flores, text = R.string.CanalTienda)
    }
}*/

@Preview(showBackground = true)
@Composable
fun FilasPreview() {
    MaterialTheme {
        Filas(collection = DatosFilas)
    }
}