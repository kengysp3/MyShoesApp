package com.example.myshoesapp.di

import com.example.myshoesapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelMod = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}