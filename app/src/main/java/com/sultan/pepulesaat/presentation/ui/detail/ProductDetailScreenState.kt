package com.sultan.pepulesaat.presentation.ui.detail

import com.sultan.pepulesaat.data.models.ProductDTO

data class ProductDetailScreenState(
    val productId: Int = 0,
    val isLoading: Boolean = false,
    val product: ProductDTO? = null,
    val addedToCartMessage: String = "",
    val addedToFavoriteMessage: String = "",
    val error: String = "",
    val isFavorite: Boolean = false
)
