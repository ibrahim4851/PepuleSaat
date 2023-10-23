package com.sultan.pepulesaat.presentation.ui.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sultan.pepulesaat.presentation.navigation.graphs.CartOrderRoutes
import com.sultan.pepulesaat.presentation.navigation.graphs.DetailsRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {

    var cardNumber by remember { mutableStateOf("") }
    var cardExpire by remember { mutableStateOf("") }
    var cardCvv by remember { mutableStateOf("") }
    var address1 by remember { mutableStateOf("") }
    var address2 by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Place Your Order Here",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.padding(18.dp))
        CreditCardTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(4f),
                value = cardExpire,
                onValueChange = { cardExpire = it }
            )
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                value = cardCvv,
                onValueChange = { cardCvv = it })
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                placeholder = { Text("Address Line 1") },
                value = address1,
                onValueChange = { address1 = it }
            )
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                placeholder = { Text("Address Line 1") },
                value = address2,
                onValueChange = { address2 = it }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                placeholder = { Text("City") },
                value = city,
                onValueChange = { city = it }
            )
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                placeholder = { Text("Province") },
                value = province,
                onValueChange = { province = it }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                placeholder = { Text("Street") },
                value = street,
                onValueChange = { street = it }
            )
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                placeholder = { Text("Number") },
                value = number,
                onValueChange = { number = it }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = { navController.navigate(CartOrderRoutes.Success.route) }) {
                Text(text = "Place Order")
            }
        }

    }
}

@Composable
@Preview(showSystemUi = true)
fun Payman() {
    PaymentScreen(navController = rememberNavController())
}