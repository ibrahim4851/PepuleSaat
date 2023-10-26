package com.sultan.pepulesaat.presentation.ui.cart

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.pepulesaat.data.models.DeleteFromCartModel
import com.sultan.pepulesaat.data.network.Resource
import com.sultan.pepulesaat.domain.usecase.ClearCartUseCase
import com.sultan.pepulesaat.domain.usecase.DeleteFromCartUseCase
import com.sultan.pepulesaat.domain.usecase.GetCartProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val deleteFromCartUseCase: DeleteFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = mutableStateOf(CartScreenState())
    val state: State<CartScreenState> = _state
    val userId = sharedPreferences.getString("userId", null)

    init {
        getCartProducts(userId.toString())
    }

    private fun getCartProducts(userId: String) {
        getCartProductsUseCase.executeGetCartProducts(userId).onEach {
            when (it) {
                is Resource.Success -> {
                    val totalPrice = it.data!!.sumByDouble { if (it.saleState) it.salePrice ?: 0.0 else it.price }
                    _state.value = CartScreenState(products = it.data, cartTotal = totalPrice)
                }

                is Resource.Error -> {
                    _state.value = CartScreenState(error = it.message ?: "Hata!")
                }

                is Resource.Loading -> {
                    _state.value = CartScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun removeFromCart(productId: Int) {
        deleteFromCartUseCase.executeDeleteFromCart(
            deleteFromCartModel = DeleteFromCartModel(
                productId
            )
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    val updatedProducts = _state.value.products.toMutableList()
                    updatedProducts.removeIf { it.id == productId }
                    val newState = it.data?.let { it1 ->
                        _state.value.copy(removedMessage = it1, isRemoveSuccessful = true, products = updatedProducts)
                    }
                    if (newState != null) {
                        _state.value = newState
                    }
                }

                is Resource.Error -> {
                    val newState = _state.value.copy(removedMessage = it.message!!)
                    _state.value = newState
                }

                is Resource.Loading -> {
                    _state.value = CartScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun clearCart() {
        clearCartUseCase.executeClearCart(userId.toString()).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = CartScreenState()
                }

                is Resource.Error -> {
                    _state.value = CartScreenState(error = it.message.toString())
                }
                is Resource.Loading -> TODO()
            }
        }.launchIn(viewModelScope)
    }


    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.RemoveFromCart -> {
                removeFromCart(event.productId)
            }
            is CartEvent.ClearCart -> {
                clearCart()
            }
        }
    }


}