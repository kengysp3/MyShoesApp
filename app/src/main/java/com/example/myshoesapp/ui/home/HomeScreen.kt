package com.example.myshoesapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myshoesapp.model.Product
import com.example.myshoesapp.ui.home.components.CategoryChips
import com.example.myshoesapp.ui.home.components.LoadingScreen
import com.example.myshoesapp.ui.home.components.ProductGrid
import com.example.myshoesapp.ui.home.components.SearchBar


@Composable
fun HomeScreen(homeUiState: HomeUiState, myIntent: (HomeIntent) ->Unit , onProductClick: (Product)-> Unit) {

    when (homeUiState) {

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
                    .padding(8.dp)
                    .background(Color.White)
            ) {

                var searchQuery by remember { mutableStateOf("Pesquisar") }

                Text(
                    text = "Olá ${homeUiState.user.nome}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp).padding(top = 12.dp).align(Alignment.CenterHorizontally)
                )

                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {

                    }
                )
                var selectedCategory by remember { mutableStateOf("Todos") }

                CategoryChips(
                    categories = homeUiState.listCategory,
                    selectedCategory = selectedCategory,
                    onCategorySelected = {
                        selectedCategory = it
                    }
                )
                ProductGrid(products = homeUiState.listItem, myIntent, onProductClick)
            }
    }

}



