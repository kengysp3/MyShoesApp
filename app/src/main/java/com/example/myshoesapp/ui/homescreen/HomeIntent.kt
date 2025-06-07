package com.example.myshoesapp.ui.homescreen


sealed class HomeIntent {
    data object LoadScreen : HomeIntent()
}