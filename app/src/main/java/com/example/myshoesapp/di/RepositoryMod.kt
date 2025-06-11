package com.example.myshoesapp.di

import com.example.myshoesapp.repository.CategorieRepository
import com.example.myshoesapp.repository.ProdutoRepository
import com.example.myshoesapp.repository.UserRepository
import org.koin.dsl.module

val repoMod = module {
    single<UserRepository> { UserRepository() }
    single<ProdutoRepository> { ProdutoRepository() }
    single<CategorieRepository> { CategorieRepository() }
}