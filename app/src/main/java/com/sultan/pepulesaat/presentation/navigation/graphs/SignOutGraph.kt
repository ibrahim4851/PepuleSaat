package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sultan.pepulesaat.presentation.ui.auth.WelcomeScreen
import com.sultan.pepulesaat.presentation.ui.auth.signout.SignOutScreen
import com.sultan.pepulesaat.presentation.ui.payment.SuccessScreen


fun NavGraphBuilder.signOutNavGraph(navController: NavController) {
    navigation(
        route = Graph.SIGN_OUT,
        startDestination = SignOutRoutes.SignOut.route
    ) {
        composable(route = SignOutRoutes.SignOut.route) {
            SignOutScreen(navController = navController)
        }
        composable(route = SignOutRoutes.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
    }
}

sealed class SignOutRoutes(val route: String) {
    object SignOut : SignOutRoutes(route = "payment_screen")
    object Welcome : SignOutRoutes(route = "welcome_screen")
}