package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sultan.pepulesaat.presentation.ui.auth.WelcomeScreen
import com.sultan.pepulesaat.presentation.ui.auth.signin.SignInScreen
import com.sultan.pepulesaat.presentation.ui.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthRoutes.Welcome.route
    ) {
        composable(route = AuthRoutes.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(route = AuthRoutes.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = AuthRoutes.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
    }
}

sealed class AuthRoutes(val route: String) {
    object SignIn : AuthRoutes(route = "sign_in_screen")
    object SignUp : AuthRoutes(route = "sign_up_screen")
    object Welcome : AuthRoutes(route = "welcome_screen")
}