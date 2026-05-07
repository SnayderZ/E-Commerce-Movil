package com.example.e_commerce.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_commerce.presentation.product.ProductListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_commerce.domain.model.Product

@Composable
fun MainScreen(navController: NavController, viewModel: ProductListViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Lista de productos",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Mostrar productos en LazyColumn
        LazyColumn(modifier = Modifier.weight(1f)) {
            // Usar directamente la lista de productos en 'items'
            items(uiState.products) { product ->
                ProductItem(
                    product = product,
                    onAddToCart = {
                        viewModel.addProductToCart(product)
                        // Navegar al carrito después de agregar el producto
                        navController.navigate("cart")
                    }
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onAddToCart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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