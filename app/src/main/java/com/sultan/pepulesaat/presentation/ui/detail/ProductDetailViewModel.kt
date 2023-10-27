package com.sultan.pepulesaat.presentation.ui.detail

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.pepulesaat.data.models.AddToCartModel
import com.sultan.pepulesaat.data.models.ProductDTO
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity
import com.sultan.pepulesaat.data.room.mapper.toFavoriteEntity
import com.sultan.pepulesaat.domain.repository.FavoriteRepository
import com.sultan.pepulesaat.domain.usecase.AddToCartUseCase
import com.sultan.pepulesaat.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    sharedPreferences: SharedPreferences,
    private val favoriteRepository: FavoriteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ProductDetailScreenState())
    val state: State<ProductDetailScreenState> = _state
    val userId: String = sharedPreferences.getString("userId", null).toString()

    init {
        savedStateHandle.get<String>("productId")?.let {
            _state.value = ProductDetailScreenState(productId = it.toInt())
        }
        getProductDetail(_state.value.productId)
        viewModelScope.launch { isFavorite(_state.value.productId) }
    }

    private fun getProductDetail(productId: Int) {
        getProductDetailUseCase.executeGetProductDetail(productId).onEach {
            when (it) {
                is Resource.Success -> {
                    val newState = _state.value.copy(product = it.data!!)
                    _state.value = newState
                }

                is Resource.Error -> {
                    val newState = _state.value.copy(error = it.message ?: "Hata!")
                    _state.value = newState
                }

                is Resource.Loading -> {
                    _state.value = ProductDetailScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun performAddToCart(productId: Int) {
        addToCartUseCase.executeAddToCart(addToCartModel = AddToCartModel(userId, productId))
            .onEach {
                when (it) {
                    is Resource.Success -> {
                        val newState = _state.value.copy(addedToCartMessage = it.data.toString())
                        _state.value = newState
                    }

                    is Resource.Error -> {
                        val newState = _state.value.copy(addedToCartMessage = it.message.toString())
                        _state.value = newState
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    private fun isFavorite(productId: Int) = viewModelScope.launch {
        val isFavorite = favoriteRepository.getFavoriteByProductId(userId, productId)
        if (isFavorite != null) {
            val newState = _state.value.copy(isFavorite = true, currentFavoriteEntity = isFavorite)
            _state.value = newState
        }
    }

    private suspend fun performAddFavorite(product: ProductDTO) {
        val favoriteEntity = product.toFavoriteEntity(userId)
        favoriteRepository.insertFavorite(favoriteEntity)
        val newState = _state.value.copy(isFavorite = true, currentFavoriteEntity = favoriteEntity)
        _state.value = newState
    }

    private suspend fun performRemoveFavorite() {
        favoriteRepository.deleteFavorite(_state.value.currentFavoriteEntity!!)
        val newState = _state.value.copy(isFavorite = false)
        _state.value = newState
    }

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.AddToFavorite -> {
                viewModelScope.launch {
                    performAddFavorite(_state.value.product!!)
                }
            }

            is ProductDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    performRemoveFavorite()
                }
            }

            is ProductDetailEvent.AddToCart -> {
                performAddToCart(event.productId)
            }
        }
    }

}