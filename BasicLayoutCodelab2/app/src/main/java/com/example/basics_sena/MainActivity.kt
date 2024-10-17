package com.example.basics_sena

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basics_sena.ui.theme.Basics_SenaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp() }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var buscador by rememberSaveable { mutableStateOf("") }
    Basics_SenaTheme {
        TextField(
            value = buscador,
            onValueChange = { buscador = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            label = {
                Text("Search")
            },
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(stringResource(R.string.placeholder_search))
            },
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 50.dp)
        )
    }

}

@Composable
fun AlignYourBodyElement(
    @DrawableRes img: Int, @StringRes title: Int, modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier) {
        Image(
            painter = painterResource(img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(
                    CircleShape
                )
        )
        Text(
            stringResource(title),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }

}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes img: Int, @StringRes title: Int, modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                spotColor = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.medium,
            )
            .background(MaterialTheme.colorScheme.onSecondary)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(180.dp)
                .padding(horizontal = 10.dp)
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item -> AlignYourBodyElement(item.drawable, item.text) }
    }
}

@Composable
fun FavoriteCollectionsCard(collection: List<DrawableStringPair>, modifier: Modifier = Modifier) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(80.dp)
    ) {
        items(
            collection
        ) { item -> FavoriteCollectionCard(item.drawable, item.text) }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.categories) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.ct1_dairy) {
            FavoriteCollectionsCard(
                collection = DairyCollection
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        HomeSection(title = R.string.ct2_vegetables) {
            FavoriteCollectionsCard(
                collection = VegetablesCollection
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        HomeSection(title = R.string.ct3_fruits) {
            FavoriteCollectionsCard(
                collection = FruitsCollection
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        HomeSection(title = R.string.ct4_animals) {
            FavoriteCollectionsCard(
                collection = AnimalsCollection
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        HomeSection(title = R.string.ct5_flower) {
            FavoriteCollectionsCard(
                collection = FlowersCollection
            )
        }
        Spacer(modifier = Modifier.height(2.dp))


    }
}

@Composable
private fun BottomNavigatorBar(modifier: Modifier = Modifier) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_navigation_home)) },
            selected = true,
            onClick = {},
        )

        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_navigation_profile)) },
            selected = false,
            onClick = {},
        )

    }
}

@Composable
private fun FloatingActionButton(modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        modifier = Modifier.clip(CircleShape),
        containerColor = MaterialTheme.colorScheme.onSurface,
        contentColor = MaterialTheme.colorScheme.background,
    ) {
        Icon(Icons.Default.AddCircle, contentDescription = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    Basics_SenaTheme() {

        Scaffold(floatingActionButton = { FloatingActionButton() },
            bottomBar = { BottomNavigatorBar() }) { padding ->
            HomeScreen(
                Modifier
                    .padding(padding)
            )
        }

    }
}


private val alignYourBodyData = listOf(
    R.drawable.ct1_lacteos to R.string.ct1_dairy,
    R.drawable.ct2_vegetales to R.string.ct2_vegetables,
    R.drawable.ct3_frutas to R.string.ct3_fruits,
    R.drawable.ct4_animal to R.string.ct4_animals,
    R.drawable.ct5_flores to R.string.ct5_flower,
).map { DrawableStringPair(it.first, it.second) }

private val DairyCollection = listOf(
    R.drawable.pcd1_milk to R.string.pcd1_milk,
    R.drawable.pcd2_chesse to R.string.pcd2_cheese,
    R.drawable.pcd3_yogurt to R.string.pcd3_yogurt,
    R.drawable.pcd4_cuajada to R.string.pcd4_cuajada,
    R.drawable.pcd5_butter to R.string.pcd5_butter,
).map { DrawableStringPair(it.first, it.second) }

private val VegetablesCollection = listOf(
    R.drawable.pcv1_onion to R.string.pcv1_onion,
    R.drawable.pcv2_tomato to R.string.pcv2_tomato,
    R.drawable.pcv3_lettuce to R.string.pcv3_lettuce,
    R.drawable.pcv4_carrot to R.string.pcv4_carrot,
    R.drawable.pcv5_bean to R.string.pcv5_bean,
).map { DrawableStringPair(it.first, it.second) }

private val FruitsCollection = listOf(
    R.drawable.pcf1_apple to R.string.pcf1_apple,
    R.drawable.pcf2_orange to R.string.pcf2_orange,
    R.drawable.pcf3_pear to R.string.pcf3_Pear,
    R.drawable.pcf4_blackberry to R.string.pcf4_Blackberry,
    R.drawable.pcf5_strawberry to R.string.pcf5_Strawberry,
).map { DrawableStringPair(it.first, it.second) }

private val AnimalsCollection = listOf(
    R.drawable.pca1_cow_meat to R.string.pca1_cow_meat,
    R.drawable.pca2_5_eggs to R.string.pca2_eggsA,
    R.drawable.pca3_honey to R.string.pca3_honey,
    R.drawable.pca4_chicken to R.string.pca4_chicken,
    R.drawable.pca2_5_eggs to R.string.pca5_eggsAA,
).map { DrawableStringPair(it.first, it.second) }

private val FlowersCollection = listOf(
    R.drawable.pcfl1_atromelia to R.string.pcfl1_astromelia,
    R.drawable.pcfl2_roses to R.string.pcfl2_roses,
    R.drawable.pcfl3_carnations to R.string.pcfl3_carnations,
    R.drawable.pcfl4_sunflower to R.string.pcfl4_sunflower,
    R.drawable.pcfl5_chirosas to R.string.pcfl5_chirosas,
).map { DrawableStringPair(it.first, it.second) }

data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)

/*@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    BasicLayoutCodelabTheme() {
        SearchBar(Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyElement(
            img = R.drawable.ab1_inversions,
            title = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutCodelabTheme {
        FavoriteCollectionCard(
            img = R.drawable.fc2_nature_meditations,
            title = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    BasicLayoutCodelabTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionsCardPreview() {
    BasicLayoutCodelabTheme {
        FavoriteCollectionsCard()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    BasicLayoutCodelabTheme() {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeScreenPreview() {
    BasicLayoutCodelabTheme {
        HomeScreen()
    }
}*/

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavBar() {
    Basics_SenaTheme() {
        BottomNavigatorBar(
            Modifier
                .padding(top = 24.dp)
        )
    }
}

/*@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MyAppPreview() {

    MyApp()

}*/



