package com.example.myapplication.navigationDemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDemo() {
    val navController : NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { bottomAppbar(navController) }
    ) {
        navigationDemoApp(navController, Modifier.padding(it))
    }
}

@Composable
fun bottomAppbar(
    navController: NavHostController
) {
    BottomAppBar(
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(NavDestinations.ONE.name)
            },
            icon = {
                Icon(Icons.Default.Home, contentDescription = null)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(NavDestinations.TWO.name) },
            icon = {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(NavDestinations.THREE.name) },
            icon = {
                Icon(Icons.Default.Settings, contentDescription = null)
            }
        )
    }
}

@Composable
fun navigationDemoApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ) {

    NavHost(
        navController = navController,
        startDestination = NavDestinations.ONE.name,
        modifier = Modifier
    ) {
        composable(NavDestinations.ONE.name) {
            screen(title = "FIRST SCREEN") {
                navController.navigate(NavDestinations.TWO.name)
            }
        }
        composable(NavDestinations.TWO.name) {
            screen(title = "SECOND SCREEN") {
                navController.navigate(NavDestinations.THREE.name)
            }
        }
        composable(NavDestinations.THREE.name) {
            screen(title = "THIRD SCREEN") {
                navController.navigate(NavDestinations.ONE.name)
            }
        }
    }
}

@Composable
fun screen(
    title: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)

    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium
        )
        Button(
            onClick = onClick,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "CLICK")
        }
    }
}

@Preview
@Composable
fun previewScreens() {
    screen("first screen", {})
}