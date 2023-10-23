package com.sultan.pepulesaat.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sultan.pepulesaat.presentation.navigation.graphs.DetailsRoutes
import com.sultan.pepulesaat.presentation.ui.home.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var searchQuery by remember { mutableStateOf("") }
    val state = viewModel.state.value

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Pepule Saat")
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .padding(values)
                    .padding(start = 8.dp, end = 8.dp)

            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        if (searchQuery.length > 3){
                            viewModel
                                .onEvent(
                                    SearchScreenEvent.SearchProduct(searchQuery = it)
                                )
                        } })

                state.products.let {
                    LazyVerticalGrid(
                        modifier = Modifier,
                        columns = GridCells.Fixed(2)
                    ) {
                        items(it){ product ->
                            ProductItem(
                                productDTO = product,
                                onClick = {
                                    navController.navigate(DetailsRoutes.ProductDetails.route + "/${product.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}