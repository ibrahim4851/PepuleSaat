package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sultan.pepulesaat.presentation.ui.detail.ProductDetailScreen

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

sealed class DetailsRoutes(val route: String) {
    object ProductDetails : DetailsRoutes(route = "product_details_screen")
}
