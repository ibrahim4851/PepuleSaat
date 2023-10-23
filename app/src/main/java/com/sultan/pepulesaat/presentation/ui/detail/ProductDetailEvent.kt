package com.sultan.pepulesaat.presentation.ui.detail

sealed class ProductDetailEvent{
    data class AddToFavorite(val productId: Int): ProductDetailEvent()
    data class AddToCart(val productId: Int): ProductDetailEvent()
}
