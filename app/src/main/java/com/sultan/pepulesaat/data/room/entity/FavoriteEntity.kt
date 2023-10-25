package com.sultan.pepulesaat.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteEntities")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val favoriteId: Long,
    val userId: String,
    val productId: Int,
    val productTitle: String,
    val imageLink: String,
    val price: String
)