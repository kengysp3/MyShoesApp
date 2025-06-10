package com.example.myshoesapp.di

import com.example.myshoesapp.repository.UserRepository
import org.koin.dsl.module

val repoMod = module {
    single<UserRepository> { UserRepository() }
}