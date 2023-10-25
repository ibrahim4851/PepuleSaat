package com.sultan.pepulesaat.presentation.navigation.graphs

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sultan.pepulesaat.presentation.navigation.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {

    var startScreen = getFirebaseUser()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startScreen
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

@Composable
private fun getFirebaseUser(): String {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    val userId = sharedPreferences.getString("userId", null)
    var startScreen = ""
    startScreen = if (userId == null) {
        Graph.AUTHENTICATION
    } else {
        Graph.HOME
    }
    return startScreen
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val PRODUCT_DETAIL = "details_graph"
    const val CART_ORDER = "cart_order_graph"
}