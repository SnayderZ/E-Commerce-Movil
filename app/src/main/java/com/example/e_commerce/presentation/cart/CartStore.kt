package com.example.e_commerce.presentation.cart

import com.example.e_commerce.domain.model.CartItem
import com.example.e_commerce.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object CartStore {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items.asStateFlow()

    fun addProduct(product: Product) {
        val currentItems = _items.value.toMutableList()

        val existingItemIndex = currentItems.indexOfFirst {
            it.product.id == product.id
        }

        if (existingItemIndex >= 0) {
            val existingItem = currentItems[existingItemIndex]

            currentItems[existingItemIndex] = existingItem.copy(
                quantity = existingItem.quantity + 1
            )
        } else {
            currentItems.add(
                CartItem(
                    product = product,
                    quantity = 1
                )
            )
        }

        _items.value = currentItems
    }

    fun removeProduct(productId: String) {
        _items.value = _items.value.filter {
            it.product.id != productId
        }
    }

    fun increaseQuantity(productId: String) {
        _items.value = _items.value.map { item ->
            if (item.product.id == productId) {
                item.copy(quantity = item.quantity + 1)
            } else {
                item
            }
        }
    }

    fun decreaseQuantity(productId: String) {
        _items.value = _items.value.mapNotNull { item ->
            if (item.product.id == productId) {
                val newQuantity = item.quantity - 1

                if (newQuantity <= 0) {
                    null
                } else {
                    item.copy(quantity = newQuantity)
                }
            } else {
                item
            }
        }
    }

    fun clearCart() {
        _items.value = emptyList()
    }
}