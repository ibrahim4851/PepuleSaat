package com.sultan.pepulesaat.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity
import com.sultan.pepulesaat.domain.repository.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favoriteEntities")
    fun getAllFavorites(): Flow<Favorites>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favoriteEntities WHERE productId = :productId")
    suspend fun getFavoriteByProductId(productId: Int): FavoriteEntity?
}