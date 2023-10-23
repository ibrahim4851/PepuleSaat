package com.sultan.pepulesaat.presentation.ui

import com.sultan.pepulesaat.data.models.ProductDTO

data class BaseProductScreenState(
    val isLoading : Boolean = false,
    val products : List<ProductDTO> = emptyList(),
    val error : String = ""
)
