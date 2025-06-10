package com.example.myshoesapp.ui.home

import com.example.myshoesapp.model.Product


sealed class HomeIntent {
    data object LoadScreen : HomeIntent()
    data class OnProductClickWithData(val product: Product) : HomeIntent()

}