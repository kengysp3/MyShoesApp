package com.example.myshoesapp.ui.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshoesapp.model.Categorie
import com.example.myshoesapp.ui.theme.MyShoesAppTheme
import com.example.myshoesapp.ui.theme.OrangeFilterSelected


@Composable
fun CategoryChips(
    categories: List<Categorie>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category.name,
                onClick = { onCategorySelected(category.name) },
                label = {
                    Text(
                        text = category.name,
                        color = if (selectedCategory == category.name) Color.White else Color.Gray
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = if (selectedCategory == category.name) OrangeFilterSelected else Color.White,
                    selectedContainerColor = OrangeFilterSelected
                ),
                shape = RoundedCornerShape(10.dp),
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCategoryChips() {
    val categories = listOf(
        Categorie("Todos"),
        Categorie("Tênis"),
        Categorie("Botas"),
        Categorie("Chuteiras"),
        Categorie("Sapatênis")
    )
    var selectedCategory by remember { mutableStateOf("Todos") }

    MyShoesAppTheme {
        CategoryChips(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
    }
}
