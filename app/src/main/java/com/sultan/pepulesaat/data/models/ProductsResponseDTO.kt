package com.sultan.pepulesaat.data.models

data class ProductsResponseDTO(
    val products: List<ProductDTO>,
    val status: Int,
    val message: String
)
