package com.example.myshoesapp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.myshoesapp.ui.home.HomeIntent
import com.example.myshoesapp.ui.home.HomeUiState
import com.example.myshoesapp.ui.theme.GrayNeutral


@Composable
fun ProductGrid( products: List<Product>, myIntent: (HomeIntent) ->Unit, onProductClick: (Product)-> Unit ) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(products) { product ->
            ProductCard(
                product,
                myIntent,
                onProductClick

            )
        }
    }
}



@Composable
fun ProductCard(
    product: Product,
    myIntent: (HomeIntent) ->Unit,
    onProductClick: (Product)-> Unit,
    modifier: Modifier = Modifier
) {

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(8.dp).clickable { onProductClick(product) }
        ) {
            Image(
                painter = painterResource(id = product.imageUrl),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                color = GrayNeutral,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
            )
        }

}


@Preview(showBackground = true)
@Composable
fun PreviewProductGrid() {
    val sampleProducts = listOf(
        Product(
            imageUrl = R.drawable.airmax,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.maxdn,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.airzoom,
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
            imageUrl = R.drawable.airzoom,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.chuteira,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),
        Product(
            imageUrl = R.drawable.airmax,
            name = "Chuteira Nike Tiempo 10",
            price = "R$ 245,99"
        ),

        )

    MaterialTheme {
        Surface {
            //ProductGrid(products = sampleProducts)
        }
    }
}