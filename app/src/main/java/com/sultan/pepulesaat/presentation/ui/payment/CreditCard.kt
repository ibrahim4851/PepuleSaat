package com.sultan.pepulesaat.presentation.ui.payment

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var formattedValue by remember(value) { mutableStateOf(formatCreditCard(value)) }

    OutlinedTextField(
        value = formattedValue,
        onValueChange = {
            formattedValue = formatCreditCard(it)
            onValueChange(it.filter { it.isDigit() })
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

private fun formatCreditCard(input: String): String {
    val digitsOnly = input.filter { it.isDigit() }
    return buildString {
        digitsOnly.chunked(4).forEachIndexed { index, chunk ->
            if (index > 0) append(" ")
            append(chunk)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreditCardTextFieldPreview() {
    var creditCardNumber by remember { mutableStateOf("") }
    CreditCardTextField(
        value = creditCardNumber,
        onValueChange = { creditCardNumber = it }
    )
}