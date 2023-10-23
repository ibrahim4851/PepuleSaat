package com.sultan.pepulesaat.presentation.ui.favorites

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun asdf(isFavorite: Boolean) {
    IconButton(
        onClick = {  }
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "FavoriteEntity",
            tint = if (isFavorite) Color.Red else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun pqowreu() {
    asdf(isFavorite = true)
}


@Preview(showBackground = true)
@Composable
fun sadfadsfa() {
    asdf(isFavorite = false)
}