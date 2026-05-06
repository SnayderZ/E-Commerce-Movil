package com.example.e_commerce.presentation.cart


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_commerce.domain.model.CartItem



@Composable
fun CartScreen(viewModel: CartViewModel = viewModel(), onBackToProducts: () -> Unit = {}) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Carrito", style = MaterialTheme.typography.headlineMedium)
            OutlinedButton(onClick = onBackToProducts) {
                Text("Volver a productos")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.items.isEmpty()) {
            Text("El carrito está vacío")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(uiState.items) { cartItem ->
                    CartItemCard(cartItem = cartItem)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Total: $${uiState.total}")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { viewModel.clearCart() }) {
                Text("Vaciar carrito")
            }
        }
    }
}

@Composable
fun CartItemCard(cartItem: CartItem) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cartItem.product.name)
            Text(text = "Precio: $${cartItem.product.price}")
            Text(text = "Cantidad: ${cartItem.quantity}")
            Text(text = "Subtotal: $${cartItem.subtotal}")
        }
    }
}