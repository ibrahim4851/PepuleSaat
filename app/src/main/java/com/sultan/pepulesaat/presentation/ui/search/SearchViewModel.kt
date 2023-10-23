package com.sultan.pepulesaat.presentation.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.usecase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    init {
        searchProducts("versace")
    }

    private fun searchProducts(query: String) {
        searchProductUseCase.executeSearchProducts(query).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = SearchState(products = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SearchState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SearchScreenEvent) {
        when(event) {
            is SearchScreenEvent.SearchProduct -> {
                searchProducts(event.searchQuery)
            }
        }
    }

}