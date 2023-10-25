package com.sultan.pepulesaat.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sultan.pepulesaat.presentation.ui.auth.WelcomeScreen
import com.sultan.pepulesaat.presentation.ui.auth.signin.SignInScreen
import com.sultan.pepulesaat.presentation.ui.auth.signout.SignOutScreen
import com.sultan.pepulesaat.presentation.ui.auth.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Welcome.route
    ) {
        composable(route = AuthScreen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = AuthScreen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object SignIn : AuthScreen(route = "sign_in_screen")
    object SignUp : AuthScreen(route = "sign_up_screen")
    object Welcome : AuthScreen(route = "welcome_screen")
}