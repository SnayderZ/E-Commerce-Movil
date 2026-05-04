package com.example.e_commerce.presentation.cart


import com.example.e_commerce.domain.model.CartItem

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val total: Double = 0.0,
    val message: String? = null
)