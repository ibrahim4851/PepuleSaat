package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sultan.pepulesaat.presentation.navigation.BottomBarScreen
import com.sultan.pepulesaat.presentation.ui.feed.FeedScreen
import com.sultan.pepulesaat.presentation.ui.payment.PaymentScreen
import com.sultan.pepulesaat.presentation.ui.payment.SuccessScreen

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

sealed class CartOrderRoutes(val route: String) {
    object Payment : CartOrderRoutes(route = "payment_screen")
    object Success : CartOrderRoutes(route = "success_screen")
}
