package com.sultan.pepulesaat.presentation.ui.search

import com.sultan.pepulesaat.data.models.ProductDTO

data class SearchState(
    val isLoading: Boolean = false,
    val products: List<ProductDTO> = listOf(),
    val error: String = ""
)
