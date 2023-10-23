package com.sultan.pepulesaat.presentation.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val state = viewModel.state.value
    val bottomBarPadding = PaddingValues(bottom = 56.dp)

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(text = "Pepule Saat")
                    },
                    scrollBehavior = scrollBehavior
                )
            },
        ) { values ->
            Column(
                modifier = Modifier
                    .padding(values)
                    .padding(bottomBarPadding)
                    .padding(start = 8.dp, end = 8.dp)

            ) {
                state.message?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.message,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
                if (state.favorites.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        columns = GridCells.Fixed(1)
                    ) {
                        items(state.favorites) { favorite ->
                            FavoriteItem(
                                favoriteEntity = favorite,
                                onRemoveClick = {
                                    viewModel.viewModelScope.launch {
                                        viewModel
                                            .onEvent(
                                                FavoriteEvent
                                                    .RemoveFavorite(favorite)
                                            )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}