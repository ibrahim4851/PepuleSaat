package com.sultan.pepulesaat.data.room.repository

import com.sultan.pepulesaat.data.room.dao.FavoriteDao
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity
import com.sultan.pepulesaat.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(
    private val dao: FavoriteDao): FavoriteRepository {

    override fun getUserFavorites(userId: String) = dao.getUserFavorites(userId)

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
        dao.insertFavorite(favoriteEntity)
    }

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        dao.deleteFavorite(favoriteEntity)
    }

    override suspend fun getFavoriteByProductId(userId: String, productId: Int): FavoriteEntity? {
        return dao.getFavoriteByProductId(userId, productId)
    }

    override suspend fun clearFavorites(userId: String) {
        return dao.clearFavorites(userId)
    }
}