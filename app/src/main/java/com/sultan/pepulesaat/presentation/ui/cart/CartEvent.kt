package com.sultan.pepulesaat.presentation.ui.cart

sealed class CartEvent {
    data class RemoveFromCart(val productId: Int): CartEvent()
    data class ClearCart(val userId: String): CartEvent()
}
