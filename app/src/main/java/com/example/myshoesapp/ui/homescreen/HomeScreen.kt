package com.example.myshoesapp.ui.homescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myshoesapp.ui.homescreen.components.CategoryChips
import com.example.myshoesapp.ui.homescreen.components.SearchBar
import com.example.myshoesapp.ui.homescreen.components.LoadingScreen
import com.example.myshoesapp.ui.homescreen.components.ProductGrid
import com.example.myshoesapp.ui.theme.MyShoesAppTheme


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
                NavigationBar {
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
                                Icon(imageVector = item.icon, contentDescription = item.label)
                            },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(BottomNavItem.Home.route) { HomeScreen(mainUiState) }
                composable(BottomNavItem.Profile.route) { ProfileScreen() }
                composable(BottomNavItem.Settings.route) { SettingsScreen() }
            }
        }
    }

}

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Home : BottomNavItem("home", Icons.Default.Home, "Início")
    data object Profile : BottomNavItem("profile", Icons.Default.Person, "Perfil")
    data object Settings : BottomNavItem("settings", Icons.Default.Settings, "Config")
}


@Preview
@Composable
fun MainScrennPreview() {

    // MainScreen()

}

@Composable
fun HomeScreen(mainUiState: HomeUiState) {

    when (mainUiState) {

        is HomeUiState.Error -> {

            Text(
                text = "Nenhum Item Localizado",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

        }

        HomeUiState.Loading -> {
            LoadingScreen()
        }

        is HomeUiState.Success ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {

                var searchQuery by remember { mutableStateOf("Pesquisar") }

                Text(
                    text = mainUiState.user.nome,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {

                    }
                )
                var selectedCategory by remember { mutableStateOf("Todos") }

                CategoryChips(
                    categories = mainUiState.listCategory,
                    selectedCategory = selectedCategory,
                    onCategorySelected = {
                        selectedCategory = it
                        // aplique filtro nos produtos, se necessário
                    }
                )
                ProductGrid(products = mainUiState.listItem)

            }
    }

}


@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Tela Perfil")
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Tela Configurações")
    }
}
