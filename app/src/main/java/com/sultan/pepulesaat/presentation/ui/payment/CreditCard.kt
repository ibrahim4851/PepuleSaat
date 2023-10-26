package com.sultan.pepulesaat.presentation.ui.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sultan.pepulesaat.R
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.steliospapamichail.creditcardmasker.utils.CardType
import com.steliospapamichail.creditcardmasker.utils.getCardTypeFromNumber
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask

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

@Composable
fun CardNumber() {
    var number by remember { mutableStateOf("") }
    OutlinedTextField(
        value = number,
        visualTransformation = CardNumberMask(" "),
        onValueChange = {
            if (it.length <= 16) number = it
        },
        label = { Text("Kredi Kartı Numarası") },
        trailingIcon = {
            val iconRes = when (getCardTypeFromNumber(number)) {
                CardType.VISA -> R.drawable.visa
                CardType.MASTERCARD -> R.drawable.mastercard
                CardType.AMERICAN_EXPRESS -> R.drawable.amex
                CardType.MAESTRO -> R.drawable.maestro
                else -> R.drawable.credit_card
            }
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = "card_type",
                modifier = Modifier
                    .height(30.dp)
                    .width(40.dp)
                    .padding(end = 10.dp)
            )
        }
    )
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