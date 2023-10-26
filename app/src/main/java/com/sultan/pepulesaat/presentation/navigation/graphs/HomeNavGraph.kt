package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sultan.pepulesaat.presentation.navigation.BottomBarScreen
import com.sultan.pepulesaat.presentation.ui.cart.CartScreen
import com.sultan.pepulesaat.presentation.ui.favorites.FavoritesScreen
import com.sultan.pepulesaat.presentation.ui.feed.FeedScreen
import com.sultan.pepulesaat.presentation.ui.search.SearchScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.FeedScreen.route
    ) {
        composable(route = BottomBarScreen.FeedScreen.route) {
            FeedScreen(navController = navController)
        }
        composable(route = BottomBarScreen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = BottomBarScreen.CartScreen.route) {
            CartScreen(navController = navController)
        }
        composable(route = BottomBarScreen.FavoritesScreen.route) {
            FavoritesScreen(navController = navController)
        }
        detailsNavGraph(navController = navController)
        cartOrderNavGraph(navController = navController)
        authNavGraph(navController = navController)
    }
}
