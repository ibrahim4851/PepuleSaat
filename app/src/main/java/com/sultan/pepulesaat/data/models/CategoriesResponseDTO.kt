package com.sultan.pepulesaat.data.models

data class CategoriesResponseDTO(
    val categories: List<String>,
    val status: Int,
    val message: String
)
