package com.example.myshoesapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myshoesapp.R
import com.example.myshoesapp.ui.home.HomeIntent
import com.example.myshoesapp.ui.home.HomeScreen
import com.example.myshoesapp.ui.home.HomeUiState
import com.example.myshoesapp.ui.home.HomeViewModel
import com.example.myshoesapp.ui.profile.ProfileScreen
import com.example.myshoesapp.ui.shoppingcart.ShoppingCartScreen
import com.example.myshoesapp.ui.theme.GrayLight
import com.example.myshoesapp.ui.theme.MyShoesAppTheme
import com.example.myshoesapp.ui.theme.OrangeFilterSelected
import com.example.myshoesapp.ui.theme.White


class MainActivity : ComponentActivity() {

    private val mainViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchedEffect(Unit) {
                mainViewModel.intents(HomeIntent.LoadScreen)
            }

            val uiState by mainViewModel.homeUiState.collectAsState()
            MainScreen(uiState)
        }
    }
}

@Preview
@Composable
fun MainScrennPreview() {

    // MainScreen()

}

@Composable
fun MainScreen(mainUiState: HomeUiState) {
    val navController = rememberNavController()
    val items = mutableListOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )
    MyShoesAppTheme {
        Scaffold(

            bottomBar = {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination
                NavigationBar(containerColor = Color.White) {
                    items.forEach { item ->
                        val itemSelec = item.route == currentRoute?.route
                        NavigationBarItem(
                            selected = itemSelec,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(modifier = Modifier.size(24.dp), painter = painterResource(item.icon), contentDescription = item.label)
                            },
                            label = {
                                Text(
                                    item.label,
                                    color = if (itemSelec) OrangeFilterSelected else Color.Gray
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = OrangeFilterSelected,
                                unselectedIconColor = GrayLight
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier
                    .padding(innerPadding)
                    .background(White)
            ) {
                composable(BottomNavItem.Home.route) { HomeScreen(mainUiState) }
                composable(BottomNavItem.Profile.route) { ProfileScreen() }
                composable(BottomNavItem.Settings.route) { ShoppingCartScreen() }
            }
        }
    }

}

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    data object Home : BottomNavItem("home", R.drawable.home, "Home")
    data object Profile : BottomNavItem("profile", R.drawable.cart, "Carrinho")
    data object Settings : BottomNavItem("settings", R.drawable.user, "Perfil")
}