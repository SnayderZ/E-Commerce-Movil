package com.example.e_commerce.presentation.cart


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        observeCart()
    }

    private fun observeCart() {
        viewModelScope.launch {
            CartStore.items.collect { items ->
                _uiState.value = CartUiState(
                    items = items,
                    total = items.sumOf { it.subtotal }
                )
            }
        }
    }

    fun increaseQuantity(productId: String) {
        CartStore.increaseQuantity(productId)
    }

    fun decreaseQuantity(productId: String) {
        CartStore.decreaseQuantity(productId)
    }

    fun removeProduct(productId: String) {
        CartStore.removeProduct(productId)
    }

    fun clearCart() {
        CartStore.clearCart()
    }
}