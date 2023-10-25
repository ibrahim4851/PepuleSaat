package com.sultan.pepulesaat.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sultan.pepulesaat.data.models.ProductDTO

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SaleProductItem(
    productDTO: ProductDTO,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(8.dp)
    val height = 100.dp
    val width = 100.dp
    Box(
        modifier = Modifier
            .height(height)
            .width(width)
            .background(Transparent, shape = shape)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomEnd,

    ) {
        GlideImage(
            model = productDTO.imageOne,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(height)
                .width(width)
                .clip(CircleShape)
        )
        Box(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .background(color = MaterialTheme.colorScheme.primaryContainer,
                    shape = shape)
                .padding(3.dp)
        ) {

            Text(
                text = productDTO.salePrice.toString(),
                color = Red
            )
        }

    }
}