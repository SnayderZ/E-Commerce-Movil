package com.example.e_commerce.presentation.product

import androidx.lifecycle.ViewModel
import com.example.e_commerce.domain.model.Product
import com.example.e_commerce.presentation.cart.CartStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ProductListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val demoProducts = listOf(
            Product(
                id = "1",
                name = "Mouse inalámbrico",
                price = 15.50,
                stock = 20
            ),
            Product(
                id = "2",
                name = "Teclado mecánico",
                price = 45.00,
                stock = 10
            ),
            Product(
                id = "3",
                name = "Audífonos gamer",
                price = 30.99,
                stock = 8
            ),
            Product(
                id = "4",
                name = "Monitor 24 pulgadas",
                price = 165.75,
                stock = 5
            )
        )

        _uiState.update { currentState ->
            currentState.copy(
                isLoading = false,
                products = demoProducts
            )
        }
    }

    fun addProductToCart(product: Product) {
        CartStore.addProduct(product)

        _uiState.update { currentState ->
            currentState.copy(
                message = "${product.name} agregado al carrito"
            )
        }
    }

    fun clearMessage() {
        _uiState.update { currentState ->
            currentState.copy(message = null)
        }
    }
}