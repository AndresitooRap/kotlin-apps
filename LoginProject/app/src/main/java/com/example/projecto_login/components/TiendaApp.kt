package com.example.projecto_login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projecto_login.R
import com.example.projecto_login.pages.login.LoginScreen

enum class PagesScreen() {
    Start,
    Dashboard
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("Proyecto_Login") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            R.string.back_button
                        )
                    )
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController = rememberNavController()
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TiendaAppBar(
                canNavigateBack = false,
                navigateUp = { /*TODO*/ }
            )
        }
    )
    { padding ->
        ScaffoldContent(
            padding = padding
        )
        NavHost(
            navController =
            navController,
            startDestination = PagesScreen.Start.name
        ) { composable(route = PagesScreen.Start.name) { LoginScreen() } }
    }
}

@Composable
fun ScaffoldContent(
//(1)
    padding: PaddingValues,
) {
    Column( //(2)
        modifier = Modifier.padding(top = 16.dp, bottom = padding.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {}
    }
}
