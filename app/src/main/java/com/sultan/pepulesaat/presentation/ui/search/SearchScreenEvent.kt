package com.sultan.pepulesaat.presentation.ui.search

sealed class SearchScreenEvent{
    data class SearchProduct(val searchQuery: String = "versace"): SearchScreenEvent()
}
