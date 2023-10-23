package com.sultan.pepulesaat.presentation.ui.favorites

import com.sultan.pepulesaat.data.room.entity.FavoriteEntity

data class FavoriteState(
    val favorites: List<FavoriteEntity> = listOf(),
    val message: String? = null
)
