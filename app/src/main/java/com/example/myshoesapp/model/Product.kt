package com.example.myshoesapp.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes

@SuppressLint("SupportAnnotationUsage")
data class Product(
    val id: Int = 0,
    val name: String,
    val price: String,
    @DrawableRes val imageUrl: Int
)