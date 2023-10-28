package com.sultan.pepulesaat.domain.repository

import com.sultan.pepulesaat.data.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

typealias Favorites = List<FavoriteEntity>

interface FavoriteRepository {

    fun getUserFavorites(userId: String): Flow<Favorites>

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    suspend fun getFavoriteByProductId(userId: String, productId: Int): FavoriteEntity?

    suspend fun clearFavorites(userId: String)

}