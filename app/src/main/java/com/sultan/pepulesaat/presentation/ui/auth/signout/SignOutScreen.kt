package com.sultan.pepulesaat.presentation.ui.auth.signout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.sultan.pepulesaat.R
import com.sultan.pepulesaat.presentation.navigation.graphs.SignOutRoutes
import com.sultan.pepulesaat.presentation.ui.auth.AuthEvent
import com.sultan.pepulesaat.presentation.ui.auth.AuthViewModel

@Composable
fun SignOutScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value

    LaunchedEffect(state.isSignOutSuccessful) {
        if (state.isSignOutSuccessful) {
            navController.navigate(SignOutRoutes.Welcome.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                viewModel.onEvent(AuthEvent.SignOut(""))
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = null)
            Text(text = "Logout")
        }
    }

}