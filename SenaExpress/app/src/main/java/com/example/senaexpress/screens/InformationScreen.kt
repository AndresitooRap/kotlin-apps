package com.example.senaexpress.screens

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senaexpress.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun InformationScreen() {
    Column(
        modifier = Modifier
            .padding(top = 90.dp, bottom = 30.dp)
            .fillMaxSize(),
    ) {
        ColumnCards()

    }


}

@Composable
fun CardInfo(@DrawableRes img: Int, @StringRes text: Int, @StringRes page: Int) {
    //Variable para el icono
    var reaction by rememberSaveable { mutableStateOf(true) }
    val iconReaction =
        if (reaction) {
            Icons.Filled.FavoriteBorder
        } else {
            Icons.Filled.Favorite
        }

    val context = LocalContext.current
    val pageUrl = stringResource(page)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(10.dp)
            )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.Red)
                ) {
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }

            Column(
                modifier = Modifier
                    .height(138.dp)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(text),
                        maxLines = 5,
                        style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = iconReaction,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            reaction = !reaction
                        })
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            //Valor webpage es un Uri, el cual se pone Uri.parse para poder cambiar el formato y lo pueda
                            // poner en la app, es decir, se pone el link pageurl, el que se parsea en uri y se entrega al uri
                            val webPage: Uri = Uri.parse(pageUrl)
                            //Valor para poder salir de la app e ir al link
                            val intent = Intent(Intent.ACTION_VIEW, webPage)
                            context.startActivity(intent)
                        })
                }
            }
        }
    }
}

@Composable
fun ColumnCards() {
    //Valor para el carrousel de las imágenes
    val images = listOf(
        R.drawable.header_information00,
        R.drawable.header_information01,
        R.drawable.header_information02,
        R.drawable.header_information03
    )

    //Valor para que funcione el carrousel
    val coroutineScope = rememberCoroutineScope()
    //Primera imagen que inicia el carrusel
    var currentPage by remember { mutableStateOf(0) }

    //Efecto para poder mover las imagenes
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Delay de 3 segundos para el cambio automático de imagen

            withContext(Dispatchers.Main) {
                coroutineScope.launch {
                    currentPage = (currentPage + 1) % images.size
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(images[currentPage]),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillWidth
        )

    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 100.dp)
    ) {
        items(Collection) { item ->
            CardInfo(img = item.img, text = item.text, page = item.page)
        }
    }
}

private val Collection = listOf(
    DrawableStringPair(R.drawable.noticia01img, R.string.New01, R.string.New01_Link),
    DrawableStringPair(R.drawable.noticia02img, R.string.New02, R.string.New02_Link),
    DrawableStringPair(R.drawable.noticia03img, R.string.New03, R.string.New03_Link),
    DrawableStringPair(R.drawable.noticia04img, R.string.New04, R.string.New04_Link),
).map { TrioItems(it.img, it.text, it.page) }


data class DrawableStringPair(
    @DrawableRes val img: Int, @StringRes val text: Int, @StringRes val page: Int
)

data class TrioItems(
    val img: Int,
    val text: Int,
    val page: Int,
)
