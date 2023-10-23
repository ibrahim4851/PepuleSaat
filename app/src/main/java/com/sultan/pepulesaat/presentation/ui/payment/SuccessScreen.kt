package com.sultan.pepulesaat.presentation.ui.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sultan.pepulesaat.R
import com.sultan.pepulesaat.presentation.navigation.BottomBarScreen

@Composable
fun SuccessScreen(navController: NavController) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animationlottie))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text(
                    text = "Successful!",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                LottieAnimation(
                    modifier = Modifier.size(400.dp),
                    composition = composition
                )
            }

        }
        Button(
            onClick = { navController.navigate(BottomBarScreen.FeedScreen.route) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        {
            Text(text = "Home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessPreview() {
    SuccessScreen(navController = rememberNavController())
}