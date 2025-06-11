package com.example.myshoesapp.ui.home



sealed class HomeIntent {
    data object LoadScreen : HomeIntent()
}