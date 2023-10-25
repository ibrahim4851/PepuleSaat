package com.sultan.pepulesaat.presentation.ui.favorites

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity
import com.sultan.pepulesaat.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoriteRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = mutableStateOf(FavoriteState())
    val state: State<FavoriteState> = _state
    val userId: String = sharedPreferences.getString("userId", null).toString()

    init {
        getFavorites()
    }

    private fun getFavorites() = viewModelScope.launch {
        repository.getUserFavorites(userId).onEach {
            if (it.isNotEmpty()) {
                _state.value = FavoriteState(favorites = it)
            } else {
                _state.value = FavoriteState(message = "You don't have favorite product")
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun removeFavorite(favoriteEntity: FavoriteEntity) {
        repository.deleteFavorite(favoriteEntity)
    }

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    removeFavorite(event.favoriteEntity)
                }
            }
            else -> {}
        }
    }

}