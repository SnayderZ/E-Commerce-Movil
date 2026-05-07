package com.example.e_commerce.presentation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.e_commerce.presentation.cart.CartViewModel
import com.example.e_commerce.domain.model.CartItem

@Composable
fun CartScreen(viewModel: CartViewModel = viewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Carrito de Compras",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (uiState.items.isEmpty()) {
            Text("El carrito está vacío")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                // Cambiar 'count = uiState.items' a simplemente pasar la lista directamente
                items(uiState.items) { cartItem ->
                    CartItemCard(cartItem = cartItem)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Total: $${uiState.total}", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.clearCart() }) {
                Text("Vaciar carrito")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Implementar proceso de pago o checkout */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Realizar Pedido")
        }
    }
}

@Composable
fun CartItemCard(cartItem: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = cartItem.product.name)
            Text("Precio: $${cartItem.product.price}")
            Text("Cantidad: ${cartItem.quantity}")
            Text("Subtotal: $${cartItem.subtotal}")
        }
    }
}