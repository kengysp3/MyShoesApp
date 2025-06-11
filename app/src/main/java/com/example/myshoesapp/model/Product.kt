package com.example.myshoesapp.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("SupportAnnotationUsage")
@Parcelize
data class Product(
    val id: Int = 0,
    val name: String,
    val price: String,
    val description: String = listOf(
        "Conforto e estilo para o dia a dia.",
        "Perfeito para quem busca performance e leveza.",
        "Design moderno com tecnologias inovadoras.",
        "Ideal para caminhadas e atividades físicas.",
        "Combina com qualquer estilo e ocasião.",
        "Durabilidade e resistência em cada detalhe.",
        "Tecnologia avançada para melhor desempenho.",
        "Inspirado na próxima geração de atletas."
    ).shuffled().toString(),
    @DrawableRes val imageUrl: Int
) : Parcelable