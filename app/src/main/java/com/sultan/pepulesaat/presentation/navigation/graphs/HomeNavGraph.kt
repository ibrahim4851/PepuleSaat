package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sultan.pepulesaat.presentation.navigation.BottomBarScreen
import com.sultan.pepulesaat.presentation.ui.cart.CartScreen
import com.sultan.pepulesaat.presentation.ui.detail.ProductDetailScreen
import com.sultan.pepulesaat.presentation.ui.favorites.FavoritesScreen
import com.sultan.pepulesaat.presentation.ui.home.FeedScreen
import com.sultan.pepulesaat.presentation.ui.payment.PaymentScreen
import com.sultan.pepulesaat.presentation.ui.payment.SuccessScreen
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
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavController) {
    navigation(
        route = Graph.PRODUCT_DETAIL,
        startDestination = DetailsRoutes.ProductDetails.route
    ) {
        composable(route = DetailsRoutes.ProductDetails.route + "/{productId}") {
            ProductDetailScreen(navController = navController)
        }
    }
}

fun NavGraphBuilder.cartOrderNavGraph(navController: NavController) {
    navigation(
        route = Graph.CART_ORDER,
        startDestination = CartOrderRoutes.Payment.route
    ) {
        composable(route = CartOrderRoutes.Payment.route) {
            PaymentScreen(navController = navController)
        }
        composable(route = CartOrderRoutes.Success.route) {
            SuccessScreen(navController = navController)
        }
        composable(route = BottomBarScreen.FeedScreen.route) {
            FeedScreen(navController = navController)
        }
    }
}

sealed class DetailsRoutes(val route: String) {
    object ProductDetails : DetailsRoutes(route = "product_details_screen")
}

sealed class CartOrderRoutes(val route: String) {
    object Payment : CartOrderRoutes(route = "payment_screen")
    object Success : CartOrderRoutes(route = "success_screen")
}
