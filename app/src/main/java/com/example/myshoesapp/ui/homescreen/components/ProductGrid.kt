package com.example.myshoesapp.ui.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshoesapp.R
import com.example.myshoesapp.model.Product


@Composable
fun ProductGrid(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductCard(
                imageRes = product.imageUrl,
                name = product.name,
                price = product.price
            )
        }
    }
}



@Composable
fun ProductCard(
    imageRes: Int,
    name: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = price,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProductGrid() {
    val sampleProducts = listOf(
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),

        )

    MaterialTheme {
        Surface {
            ProductGrid(products = sampleProducts)
        }
    }
}