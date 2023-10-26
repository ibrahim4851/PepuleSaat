package com.sultan.pepulesaat.presentation.ui.feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.usecase.GetProductsUseCase
import com.sultan.pepulesaat.presentation.ui.FeedScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FeedScreenState())
    val state : State<FeedScreenState> = _state

    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        getProductsUseCase.executeGetProducts().onEach {
            when(it){
                is Resource.Success -> {
                    val saleProducts = it.data!!.filter { it.saleState }
                    _state.value = FeedScreenState(products = it.data, saleProducts = saleProducts)
                }
                is Resource.Error -> {
                    _state.value = FeedScreenState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = FeedScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}