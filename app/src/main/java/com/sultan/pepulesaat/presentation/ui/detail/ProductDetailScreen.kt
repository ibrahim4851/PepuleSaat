package com.sultan.pepulesaat.presentation.ui.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sultan.pepulesaat.ui.RatingBar

@OptIn( ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(state.addedToCartMessage) {
        if (state.addedToCartMessage.isNotBlank()) {
            Toast.makeText(context, state.addedToCartMessage, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(state.isFavorite) {

    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { values ->
            state.product?.let {
                Column(
                    modifier = Modifier
                        .padding(values)
                        .padding(start = 10.dp, end = 10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {

                        Row(modifier = Modifier.fillMaxWidth()) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }

                        GlideImage(
                            model = state.product.imageOne,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        {
                            Column {
                                Text(text = state.product.title, fontWeight = FontWeight.Bold)
                                RatingBar(
                                    rating = state.product.rate.toFloat(),
                                    modifier = Modifier.height(15.dp)
                                )
                            }
                            if (state.product.saleState) {
                                Column {
                                    Text(
                                        text = state.product.price.toString() + "₺",
                                        fontSize = 10.sp,
                                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                                    )
                                    Text(text = state.product.salePrice.toString() + "₺")
                                }
                            } else {
                                Text(text = state.product.price.toString() + "₺")
                            }
                        }
                        Text(text = state.product.description)
                        Text(text = "Category: ${state.product.category}")
                        Text(text = "Stock: ${state.product.count}")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            modifier = Modifier.weight(4f),
                            onClick = {
                                viewModel.onEvent(
                                    ProductDetailEvent
                                        .AddToCart(state.product.id)
                                )

                            }) {
                            Text(text = "Add To Cart")
                        }
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                viewModel.onEvent(
                                    ProductDetailEvent
                                        .AddToFavorite(state.product.id)
                                )

                            }
                        )
                        {
                            Icon(
                                imageVector = if (state.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "",
                                tint = if (state.isFavorite) Color.Red else Color.Gray
                            )
                        }
                    }
                }

            }
        }
    }

}