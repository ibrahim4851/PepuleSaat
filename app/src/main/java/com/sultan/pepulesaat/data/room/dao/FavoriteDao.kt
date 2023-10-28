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

    @Query("SELECT * FROM favoriteEntities WHERE userId = :userId")
    fun getUserFavorites(userId: String): Flow<Favorites>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favoriteEntities WHERE productId = :productId AND userId = :userId")
    suspend fun getFavoriteByProductId(userId: String, productId: Int): FavoriteEntity?

    @Query("DELETE FROM favoriteEntities WHERE userId = :userId")
    suspend fun clearFavorites(userId: String)

}