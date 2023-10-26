package com.sultan.pepulesaat.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object FeedScreen :
        BottomBarScreen(
            title = "Pepule",
            icon = Icons.Filled.Home,
            route = "feed_screen"
        )

    object SearchScreen :
        BottomBarScreen(
            title = "Arama",
            icon = Icons.Filled.Search,
            route = "search_screen"
        )

    object CartScreen :
        BottomBarScreen(
            title = "Sepet",
            icon = Icons.Filled.ShoppingCart,
            route = "cart_screen"
        )

    object FavoritesScreen :
        BottomBarScreen(
            title = "Favoriler",
            icon = Icons.Filled.Favorite,
            route = "favorite_screen"
        )
}
