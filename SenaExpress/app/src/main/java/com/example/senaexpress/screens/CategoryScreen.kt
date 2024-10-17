package com.example.senaexpress.screens

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.senaexpress.R
import com.example.senaexpress.components.Category
import com.example.senaexpress.components.CreateNotificationChannel
import com.example.senaexpress.components.ExtensiveNotification
import com.example.senaexpress.components.Product

@Composable
fun CategoryScreen() {
    //Lista de las categorias que hay en la app
    val categoryList = listOf(
        Category(
            imgCategory = R.drawable.ic_cow,
            textCategory = R.string.Dairy,
        ),
        Category(
            imgCategory = R.drawable.ic_fruits,
            textCategory = R.string.Fruits,
        ),
        Category(
            imgCategory = R.drawable.ic_vegetables,
            textCategory = R.string.Vegetables,
        ),
        Category(
            imgCategory = R.drawable.ic_flower,
            textCategory = R.string.Flowers,
        ),
        Category(
            imgCategory = R.drawable.ic_animals,
            textCategory = R.string.Animals,
        ),
        Category(
            imgCategory = R.drawable.ic_bock_lock,
            textCategory = R.string.Others,
        )
    )
    //Valor para recordar que el index, el cual inicia en 0
    val selectIndex = remember { mutableStateOf(0) }
    //Valor para recordar la categoria seleccionada
    val selectedCategory = categoryList.getOrNull(selectIndex.value)
    Column(
        modifier = Modifier
            .padding(top = 90.dp, bottom = 30.dp)
            .fillMaxSize(),
    ) {
        Row {
            CategoryList(
                categorylist = categoryList,
                selectedIndex = selectIndex
            )
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Column {
                //Se pone así para saber que categoria esta en este momemnto
                selectedCategory?.let { category ->
                    ViewProducts(
                        product = getProductListForCategory(category)
                    )
                }
            }
        }
    }
}

//Modelo para la categoria
@Composable
fun Categories(
    category: Category,
    selectedIndex: MutableState<Int>,
    index: Int,
) {
    Column() {
        Box(
            modifier = Modifier
                .width(90.dp)
                .padding(horizontal = 7.dp, vertical = 7.dp)
                .clickable {
                    selectedIndex.value = index
                }
                .border(
                    //Los if y else es para que cuando se seleccione se muestre y si no, no se muestra
                    width = if (selectedIndex.value == index) 1.dp else 0.dp,
                    color = if (selectedIndex.value == index) Color(0xFF2FAA16) else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)

                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(category.imgCategory),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 1.dp)
                        .size(40.dp),
                    tint = if (selectedIndex.value == index) Color(0xFF2FAA16) else MaterialTheme.colorScheme.onPrimary
                )
                Row() {
                    Text(
                        text = stringResource(id = category.textCategory),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                        color = if (selectedIndex.value == index) Color(0xFF2FAA16) else MaterialTheme.colorScheme.onPrimary
                    )
                }

            }

        }
    }
}

//Lista de category se usa para el for in y hacer el recorrido
@Composable
fun CategoryList(categorylist: List<Category>, selectedIndex: MutableState<Int>) {


    Column(
        modifier = Modifier
            .width(90.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.onSecondary),
    ) {
        //Se usa un for in para poder hacer un recorrido en todas las que hay
        for (index in categorylist.indices) {
            Categories(
                category = categorylist[index],
                selectedIndex = selectedIndex,
                index = index,
            )
        }
    }
}

//Modelo para el producto
@Composable
fun ProductItem(
    product: Product
) {
    var reaction by rememberSaveable { mutableStateOf(true) }
    val iconReaction =
        if (reaction) {
            Icons.Outlined.ShoppingCart

        } else {
            Icons.Default.ShoppingCart
        }

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(16.dp)
            )
            .height(400.dp)
            .width(300.dp)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)


    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box() {
                        Image(
                            painter = painterResource(product.Profile_image),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp),
                            contentScale = ContentScale.Crop,
                        )

                    }
                    Text(
                        stringResource(id = product.Profile_text),
                        modifier = Modifier.padding(start = 10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        maxLines = 2
                    )


                }
                Text(
                    "$" + product.Price.toString(),
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(product.Product_image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RectangleShape),
                    contentScale = ContentScale.Fit,

                    )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 15.dp)
            ) {
                Text(
                    stringResource(id = product.Product_text),
                    textAlign = TextAlign.Start
                )
                Box(modifier = Modifier.align(alignment = Alignment.BottomEnd)) {
                    Icon(
                        imageVector = iconReaction,
                        contentDescription = null,
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .clickable {
                                reaction = !reaction
                            }
                    )
                }
            }
        }

    }
}

@Composable
fun ViewProducts(product: List<Product>) {
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.CanalTienda)
    val name = stringResource(R.string.CanalTienda)
    val descriptionText = stringResource(R.string.Canal_de_Notificaciones_Tienda_CBA)

    LaunchedEffect(Unit) {
        CreateNotificationChannel(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    val textLong: String =
        "Categoria Restringida: " +
                "Esta categoria ha sido restringida debido a la alta demanda " +
                "de nuestros productos, lamentamos las intenciones no logradas " +
                "Atentamente, SENA CBA"
    val bigIcono = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.logo
    )
    LaunchedEffect(Unit) {
        CreateNotificationChannel(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    //Si encuentra la lista vacia por el return when se mostra los siguiente:
    if (product.isEmpty()) {
        ExtensiveNotification(
            context,
            idChannel,
            idNotification + 1,
            "Categoria Restringida",
            textLong, bigIcono,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Cancel,
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = Color.Red
            )
        }
        //Si no se encuentra la lista vacia, se mostrara esto:
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 100.dp)
        ) {
            items(product) { item ->
                ProductItem(product = item)
            }
        }
    }
}

//Funcion para devolver los productos de la categoria escogida
fun getProductListForCategory(category: Category): List<Product> {
    //hace un return cuando encuentra el mismo texto de la categoria y la muestra
    //Se hace una lista, con el modelo del producto
    return when (category.textCategory) {
        R.string.Dairy -> listOf(
            Product(
                Profile_image = R.drawable.lacteos,
                Profile_text = R.string.Milk,
                Price = 2500,
                Product_image = R.drawable.milkpng,
                Product_text = R.string.Milk_About
            ),
            Product(
                Profile_image = R.drawable.lacteos,
                Profile_text = R.string.Cheese,
                Price = 6000,
                Product_image = R.drawable.cheesepng,
                Product_text = R.string.Cheese_About
            ),
            Product(
                Profile_image = R.drawable.lacteos,
                Profile_text = R.string.Yogurt,
                Price = 3200,
                Product_image = R.drawable.yogurt,
                Product_text = R.string.Yogurt_About
            ),
            Product(
                Profile_image = R.drawable.lacteos,
                Profile_text = R.string.Kummis,
                Price = 6000,
                Product_image = R.drawable.kummis,
                Product_text = R.string.Kummis_About
            ),
            Product(
                Profile_image = R.drawable.yogurtgriego,
                Profile_text = R.string.Yogurt_Griego,
                Price = 6000,
                Product_image = R.drawable.yogurtgriego,
                Product_text = R.string.Yogurt_Griego_About
            ),

            )

        R.string.Vegetables -> listOf(
            Product(
                Profile_image = R.drawable.vegetables,
                Profile_text = R.string.Carrots,
                Price = 2500,
                Product_image = R.drawable.zanahoria,
                Product_text = R.string.Carrots_About
            ),
            Product(
                Profile_image = R.drawable.vegetables,
                Profile_text = R.string.Onion,
                Price = 3100,
                Product_image = R.drawable.cebolla,
                Product_text = R.string.Onion_About
            ),
            Product(
                Profile_image = R.drawable.vegetables,
                Profile_text = R.string.Garlic,
                Price = 1200,
                Product_image = R.drawable.ajo,
                Product_text = R.string.Garlic_About
            ),
            Product(
                Profile_image = R.drawable.vegetables,
                Profile_text = R.string.Lettuce,
                Price = 2450,
                Product_image = R.drawable.lechuga,
                Product_text = R.string.Lettuce_About
            ),
            Product(
                Profile_image = R.drawable.vegetables,
                Profile_text = R.string.Bean,
                Price = 3100,
                Product_image = R.drawable.habichuela,
                Product_text = R.string.Bean_About
            ),
        )

        R.string.Fruits -> listOf(
            Product(
                Profile_image = R.drawable.fruits,
                Profile_text = R.string.Strawberry,
                Price = 4100,
                Product_image = R.drawable.fresas,
                Product_text = R.string.Strawberry_About
            ),
            Product(
                Profile_image = R.drawable.fruits,
                Profile_text = R.string.Blackberry,
                Price = 2000,
                Product_image = R.drawable.mora,
                Product_text = R.string.Blackberry_About
            ),
            Product(
                Profile_image = R.drawable.fruits,
                Profile_text = R.string.Tangerine,
                Price = 2100,
                Product_image = R.drawable.mandarina,
                Product_text = R.string.Tangerine_About
            ),
            Product(
                Profile_image = R.drawable.fruits,
                Profile_text = R.string.Apple,
                Price = 1500,
                Product_image = R.drawable.manzanapng,
                Product_text = R.string.Apple_About
            ),
            Product(
                Profile_image = R.drawable.fruits,
                Profile_text = R.string.Banana,
                Price = 3100,
                Product_image = R.drawable.banano,
                Product_text = R.string.Banana_About
            ),
        )

        R.string.Flowers -> listOf(
            Product(
                Profile_image = R.drawable.flores,
                Profile_text = R.string.Astromelia,
                Price = 1000,
                Product_image = R.drawable.astromelias,
                Product_text = R.string.Astromelia_About
            ),
            Product(
                Profile_image = R.drawable.flores,
                Profile_text = R.string.Orchids,
                Price = 1320,
                Product_image = R.drawable.orquidea,
                Product_text = R.string.Orchids_About
            ),
            Product(
                Profile_image = R.drawable.flores,
                Profile_text = R.string.Tulipa,
                Price = 2100,
                Product_image = R.drawable.tulipan,
                Product_text = R.string.Tulipa_About
            ),
            Product(
                Profile_image = R.drawable.flores,
                Profile_text = R.string.Cattleya,
                Price = 4100,
                Product_image = R.drawable.cattleya,
                Product_text = R.string.Cattleya_About
            ),
            Product(
                Profile_image = R.drawable.flores,
                Profile_text = R.string.Sunflower,
                Price = 3650,
                Product_image = R.drawable.girasol,
                Product_text = R.string.Sunflower_About
            ),
        )

        R.string.Animals -> listOf(
            Product(
                Profile_image = R.drawable.animalsjpg,
                Profile_text = R.string.EggsA,
                Price = 12000,
                Product_image = R.drawable.huevos,
                Product_text = R.string.EggsA_About
            ),
            Product(
                Profile_image = R.drawable.animalsjpg,
                Profile_text = R.string.EggsAA,
                Price = 13000,
                Product_image = R.drawable.huevos,
                Product_text = R.string.EggsAA_About
            ),
            Product(
                Profile_image = R.drawable.animalsjpg,
                Profile_text = R.string.EggsAAA,
                Price = 14000,
                Product_image = R.drawable.huevos,
                Product_text = R.string.EggsAAA_About
            ),
            Product(
                Profile_image = R.drawable.animalsjpg,
                Profile_text = R.string.Beef,
                Price = 13000,
                Product_image = R.drawable.carneres,
                Product_text = R.string.Beef_About
            ),
            Product(
                Profile_image = R.drawable.animalsjpg,
                Profile_text = R.string.Chicken,
                Price = 20000,
                Product_image = R.drawable.pollo,
                Product_text = R.string.Chicken_About
            ),
        )

        //Si no encuentra nada hace una lista vacia
        else -> emptyList()
    }
}

