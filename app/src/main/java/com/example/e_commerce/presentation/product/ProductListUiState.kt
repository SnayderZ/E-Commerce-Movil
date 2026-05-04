package com.example.e_commerce.presentation.product

import android.os.Message
import com.example.e_commerce.domain.model.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val message: String? = null
)