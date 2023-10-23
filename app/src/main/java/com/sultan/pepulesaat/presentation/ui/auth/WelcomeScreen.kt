package com.sultan.pepulesaat.presentation.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sultan.pepulesaat.R
import com.sultan.pepulesaat.presentation.navigation.graphs.AuthScreen

@Composable
fun WelcomeScreen(
    navController: NavController
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_welcome_black))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(
            modifier = Modifier.size(400.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            navController.navigate(AuthScreen.SignIn.route)
        }) {
            Text(text = "Sign In")
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(AuthScreen.SignUp.route)
            }
        ) {
            Text(text = "Register")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen(navController = rememberNavController())
}