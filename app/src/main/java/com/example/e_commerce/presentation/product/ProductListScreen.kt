package com.example.e_commerce.presentation.product


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.e_commerce.domain.model.Product

@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductListViewModel = viewModel()){
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Lista de Productos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Mostrar los producstos en LazyColum
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(uiState.products) { product ->
                ProductItem(
                    product = product,
                    onAddToCart = {
                        viewModel.addProductToCart(product)
                        navController.navigate("cart")
                    }
                )
            }
        }
    }
}
@Composable
fun ProductItem(product: Product,onAddToCart: ()-> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name)
            Text(text = "Precio: $${product.price}")
            Button(onClick = onAddToCart) {
                Text("Agregar al carrito")
            }
        }
    }
}