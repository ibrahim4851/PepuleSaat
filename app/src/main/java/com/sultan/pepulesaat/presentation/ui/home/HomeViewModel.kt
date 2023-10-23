package com.sultan.pepulesaat.presentation.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.usecase.GetProductsUseCase
import com.sultan.pepulesaat.domain.usecase.GetSaleProductsUseCase
import com.sultan.pepulesaat.presentation.ui.BaseProductScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getSaleProductsUseCase: GetSaleProductsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BaseProductScreenState())
    val state : State<BaseProductScreenState> = _state

    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        getProductsUseCase.executeGetProducts().onEach {
            when(it){
                is Resource.Success -> {
                    _state.value = BaseProductScreenState(products = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BaseProductScreenState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = BaseProductScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}