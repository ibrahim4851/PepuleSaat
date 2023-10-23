package com.sultan.pepulesaat.presentation.ui.favorites

import com.sultan.pepulesaat.data.room.entity.FavoriteEntity


sealed class FavoriteEvent{
    data class RemoveFavorite(val favoriteEntity: FavoriteEntity): FavoriteEvent()
    data class AddFavorite(val favoriteEntity: FavoriteEntity): FavoriteEvent()
}
