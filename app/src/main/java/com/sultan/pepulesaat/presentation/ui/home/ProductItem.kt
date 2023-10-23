package com.sultan.pepulesaat.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sultan.pepulesaat.data.models.ProductDTO
import com.sultan.pepulesaat.ui.RatingBar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    productDTO: ProductDTO,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            GlideImage(model = productDTO.imageOne, contentDescription = null)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column {
                    Text(text = productDTO.title, fontWeight = FontWeight.Bold)
                    RatingBar(rating = productDTO.rate.toFloat(), modifier = Modifier.height(15.dp))
                }
                if (productDTO.saleState) {
                    Column {
                        Text(
                            text = productDTO.price.toString() + "₺",
                            fontSize = 10.sp,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                        Text(text = productDTO.salePrice.toString() + "₺")
                    }
                } else {
                    Text(text = productDTO.price.toString() + "₺")
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))

        }
    }
}

@Preview
@Composable
fun ProductItemPreview(){
    ProductItem(productDTO =
    ProductDTO(0,
        "asdf",
        1389.99,
        15000.toDouble(),
        "A very cool watch",
        "Male Watch",
        "https://cdn.saatvesaat.com.tr/media/catalog/product/v/r/vrscv12060017_1.jpg",
        "",
        "",
        3.8,
        100,
        true), onClick = {})
}