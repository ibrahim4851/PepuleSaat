package com.sultan.pepulesaat.data.room.repository

import com.sultan.pepulesaat.data.room.dao.FavoriteDao
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity
import com.sultan.pepulesaat.domain.repository.FavoriteRepository
import com.sultan.pepulesaat.domain.repository.Favorites
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(
    private val dao: FavoriteDao): FavoriteRepository {

    override fun getAllFavorites() = dao.getAllFavorites()

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
        dao.insertFavorite(favoriteEntity)
    }

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        dao.deleteFavorite(favoriteEntity)
    }

    override suspend fun getFavoriteByProductId(productId: Int): FavoriteEntity? {
        return dao.getFavoriteByProductId(productId)
    }
}