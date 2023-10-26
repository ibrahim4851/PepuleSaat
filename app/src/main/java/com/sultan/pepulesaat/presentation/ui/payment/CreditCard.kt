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
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CreditCardTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var cursorPosition by remember { mutableStateOf(0) }

    var formattedValue by remember(value, cursorPosition) {
        mutableStateOf(formatCreditCard(value, cursorPosition))
    }

    OutlinedTextField(
        value = formattedValue,
        label = { Text(text = "Kredi Kartı Numarası")},
        onValueChange = {
            val filteredValue = it.filter { it.isDigit() }
            cursorPosition = it.length
            formattedValue = formatCreditCard(filteredValue, cursorPosition)
            onValueChange(filteredValue)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

private fun formatCreditCard(input: String, cursorPosition: Int): String {
    val digitsOnly = input.filter { it.isDigit() }
    val formatted = buildString {
        digitsOnly.chunked(4).forEachIndexed { index, chunk ->
            if (index > 0) append(" ")
            append(chunk)
        }
    }
    val adjustedCursorPosition = cursorPosition + cursorPosition / 4
    return if (adjustedCursorPosition < formatted.length) {
        formatted.substring(0, adjustedCursorPosition) + " " + formatted.substring(adjustedCursorPosition)
    } else {
        formatted
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