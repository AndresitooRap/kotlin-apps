package com.example.senaexpress.screens

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.senaexpress.R
import com.example.senaexpress.components.ProductHome
import com.example.senaexpress.components.Screens

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp, bottom = 40.dp)
    ) {
        item {
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.Our_Recommendations),
                        style = TextStyle(fontSize = 27.sp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                OurRecommendationsList(
                    ourrecomendations = listOf(
                        ProductHome(text = R.string.EggsAA, img = R.drawable.eggsaa_home),
                        ProductHome(text = R.string.Milk, img = R.drawable.leche_home),
                        ProductHome(text = R.string.Astromelia, img = R.drawable.astromelias_home)
                    ), navController = navController
                )
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                    modifier = Modifier.padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                HomeStart()
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                )
                Footer()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.onSecondary)
                        .padding(top = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column() {
                            Box(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo_sena),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
                                )
                            }

                        }

                    }
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 5.dp)

                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = stringResource(id = R.string.AGRICULTURAL_BIOTECHNOLOGY_CENTER_SENA),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 20.dp)

                    )
                }

            }
        }

    }

}

@Composable
fun HomeStartCard(@StringRes BoxTitle: Int, @StringRes BoxBody: Int) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF369D20))

        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 25.dp, vertical = 20.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onSecondary,
                        shape = RoundedCornerShape(5.dp)
                    )

            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(id = BoxTitle),
                            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 24.sp),
                            color = MaterialTheme.colorScheme.onTertiary,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                    ) {
                        Text(
                            stringResource(id = BoxBody),
                            style = TextStyle(fontWeight = FontWeight.Light, fontSize = 15.sp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Start
                        )
                    }

                }

            }
        }
    }
}

@Composable
fun HomeStart() {
    Column {
        CollectionHome.forEach { item ->
            HomeStartCard(
                BoxTitle = item.BoxTitle,
                BoxBody = item.BoxBody
            )
        }
    }
}

private val CollectionHome =
    listOf(
        R.string.Start1 to R.string.StartBody1,
        R.string.Start2 to R.string.StartBody2,
        R.string.Start3 to R.string.StartBody3,
        R.string.Start4 to R.string.StartBody4
    ).map { PairHome(it.first, it.second) }

private data class PairHome(
    @StringRes val BoxTitle: Int, @StringRes val BoxBody: Int,
)

//Se trae una lista, la cual es ProductHome, que es por decirlo, un modelo
@Composable
fun OurRecommendationsList(ourrecomendations: List<ProductHome>, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        for (index in ourrecomendations.indices) {
            ourRecommendationsItem(
                ourrecommendationsitem = ourrecomendations[index],
                navController = navController
            )
        }
    }
}

//Se trae la claase producthome el cual tiene parametros, como la imagen, el nombre, etc.
@Composable
fun ourRecommendationsItem(ourrecommendationsitem: ProductHome, navController: NavHostController) {
    Box(modifier = Modifier.padding(16.dp)) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(ourrecommendationsitem.text),
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp), horizontalArrangement = Arrangement.End
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp),
                    )
                }
            }

            Box(
                modifier = Modifier
                    .height(180.dp)
                    .clickable { navController.navigate(Screens.CategoryScreen.route) }
            ) {
                Image(
                    painter = painterResource(ourrecommendationsitem.img),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}

@Composable
fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 35.dp)
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.Phone),
                style = TextStyle(fontWeight = FontWeight.Bold),
            )
            Text("(+60) (1) 546 23 23")
        }
        Row {
            Text("Whatsapp: ", style = TextStyle(fontWeight = FontWeight.Bold))
            Text("316 876 0255")
        }
        Row {
            Text("Email: ", style = TextStyle(fontWeight = FontWeight.Bold))
            Text("servicioalciudadano@sena.edu.co")
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Row {
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = R.string.Attention_schedule))
                }
                append(stringResource(id = R.string.Monday_Saturday))
            }

            Text(
                text = annotatedString
            )
        }
        RowSocial()

    }
}

//Modelo para el circulo de la red social
@Composable
fun SocialMedia(@DrawableRes icon: Int, @StringRes page: Int) {
    val context = LocalContext.current
    val pageUrl = stringResource(page)

    Box(
        modifier = Modifier
            .height(70.dp)
            .width(70.dp)
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .background(Color(0xFF369D20), shape = CircleShape)
                .clickable {
                    val webPage: Uri = Uri.parse(pageUrl)
                    val intent = Intent(Intent.ACTION_VIEW, webPage)
                    context.startActivity(intent)

                }
                .padding(horizontal = 2.dp)
        ) {

            Icon(
                painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                tint = Color.White
            )
        }
    }

}

@Composable
fun RowSocial() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SocialCollection.forEach { item ->
            SocialMedia(icon = item.icon, page = item.page)
        }
    }
}

private val SocialCollection = listOf(
    R.drawable.ic_facebook to R.string.Facebook,
    R.drawable.ic_instagram to R.string.Instagram,
    R.drawable.ic_tiktok to R.string.TikTok,
    R.drawable.ic_twitter to R.string.Twitter,
    R.drawable.ic_youtube to R.string.Youtube,

    ).map { PairSocial(it.first, it.second) }

private data class PairSocial(
    @DrawableRes val icon: Int, @StringRes val page: Int
)

