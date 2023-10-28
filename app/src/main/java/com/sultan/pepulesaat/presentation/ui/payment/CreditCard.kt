package com.sultan.pepulesaat.presentation.ui.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.steliospapamichail.creditcardmasker.utils.CardType
import com.steliospapamichail.creditcardmasker.utils.getCardTypeFromNumber
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.sultan.pepulesaat.R

@Composable
fun CardNumber(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        visualTransformation = CardNumberMask(" "),
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        label = { Text("Kredi Kartı Numarası") },
        trailingIcon = {
            val iconRes = when (getCardTypeFromNumber(value)) {
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
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}