package com.sultan.pepulesaat.presentation.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sultan.pepulesaat.R
import com.sultan.pepulesaat.data.models.ProductDTO

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartProductItem(
    productDTO: ProductDTO,
    onRemoveClick: () -> Unit
) {
    ElevatedCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                GlideImage(
                    modifier = Modifier
                        .weight(3f),
                    model = productDTO.imageOne,
                    contentDescription = null,
                )
                Column(
                    modifier = Modifier
                        .weight(7f)
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = productDTO.title)
                        if (productDTO.salePrice != null) {
                            Text(text = productDTO.salePrice.toString() + "₺")
                        } else {
                            Text(text = productDTO.price.toString() + "₺")
                        }
                    }
                    IconButton(
                        onClick = { onRemoveClick() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                    }
                }
            }
        }

    }
}