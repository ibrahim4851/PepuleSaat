package com.sultan.pepulesaat.presentation.ui.cart

import com.sultan.pepulesaat.data.models.ProductDTO

data class CartScreenState(val isLoading : Boolean = false,
                           val products : List<ProductDTO> = mutableListOf(),
                           val removedMessage: String = "",
                           val isRemoveSuccessful: Boolean = false,
                           val cartTotal: Double = 0.0,
                           val error : String = "")
