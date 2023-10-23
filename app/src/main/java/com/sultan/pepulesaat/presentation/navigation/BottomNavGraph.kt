package com.sultan.pepulesaat.presentation.navigation

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sultan.pepulesaat.presentation.ui.auth.WelcomeScreen
import com.sultan.pepulesaat.presentation.ui.auth.signin.SignInScreen
import com.sultan.pepulesaat.presentation.ui.auth.signout.SignOutScreen
import com.sultan.pepulesaat.presentation.ui.auth.signup.SignUpScreen
import com.sultan.pepulesaat.presentation.ui.cart.CartScreen
import com.sultan.pepulesaat.presentation.ui.detail.ProductDetailScreen
import com.sultan.pepulesaat.presentation.ui.favorites.FavoritesScreen
import com.sultan.pepulesaat.presentation.ui.home.FeedScreen
import com.sultan.pepulesaat.presentation.ui.payment.PaymentScreen
import com.sultan.pepulesaat.presentation.ui.payment.SuccessScreen
import com.sultan.pepulesaat.presentation.ui.search.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {

    /*val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    val userId = sharedPreferences.getString("userId", null)
    var startScreen = ""
    startScreen = if (userId == null) {
        Screen.WelcomeScreen.screenRoute
    } else {
        Screen.HomeScreen.screenRoute
    }

    NavHost(
        navController = navController,
        startDestination = startScreen
    ) {
        composable(route = Screen.HomeScreen.screenRoute) {
            FeedScreen(navController)
        }
        composable(route = Screen.SearchScreen.screenRoute) {
            SearchScreen(navController)
        }
        composable(route = Screen.CartScreen.screenRoute) {
            CartScreen(navController)
        }
        composable(route = Screen.FavoritesScreen.screenRoute) {
            FavoritesScreen(navController)
        }
        composable(Screen.DetailScreen.screenRoute + "/{productId}") {
            ProductDetailScreen(navController = navController)
        }
        composable(Screen.PaymentScreen.screenRoute) {
            PaymentScreen(navController = navController)
        }
        composable(Screen.SuccessScreen.screenRoute) {
            SuccessScreen(navController = navController)
        }
        composable(Screen.SignInScreen.screenRoute) {
            SignInScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.screenRoute) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.SignOutScreen.screenRoute) {
            SignOutScreen(navController = navController)
        }
        composable(Screen.WelcomeScreen.screenRoute) {
            WelcomeScreen(navController = navController)
        }
    }*/
}