package com.example.myshoesapp.repository

import com.example.myshoesapp.model.Categorie

class CategorieRepository {

    fun searchCategories() = listOf(
        Categorie("Todos"),
        Categorie("Tênis"),
        Categorie("Botas"),
        Categorie("Chuteiras"),
        Categorie("Sapatênis")
    )
}