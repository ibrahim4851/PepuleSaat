package com.sultan.pepulesaat.presentation.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sultan.pepulesaat.data.room.entity.FavoriteEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavoriteItem(
    favoriteEntity: FavoriteEntity,
    onRemoveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            GlideImage(
                modifier = Modifier
                    .weight(3f),
                model = favoriteEntity.imageLink,
                contentDescription = null,
            )
            Column(
                modifier = Modifier.weight(7f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = favoriteEntity.productTitle)
                    Text(text = favoriteEntity.price + "₺")
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