package com.example.myshoesapp.ui.home

import com.example.myshoesapp.model.Categorie
import com.example.myshoesapp.model.Product
import com.example.myshoesapp.model.User

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(
        val user: User,
        val listItem: List<Product>,
        val listCategory: List<Categorie>
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}