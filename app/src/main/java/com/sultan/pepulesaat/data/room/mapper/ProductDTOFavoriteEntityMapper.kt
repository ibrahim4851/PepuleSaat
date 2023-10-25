package com.sultan.pepulesaat.data.room.mapper

import com.sultan.pepulesaat.data.models.ProductDTO
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity

fun ProductDTO.toFavoriteEntity(userId: String): FavoriteEntity{
    return FavoriteEntity(
        favoriteId = 0,
        userId = userId,
        productId = id,
        productTitle = title,
        imageLink = imageOne!!,
        price = price.toString()
    )
}