package com.sultan.pepulesaat.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sultan.pepulesaat.presentation.navigation.graphs.AuthScreen
import com.sultan.pepulesaat.presentation.navigation.graphs.DetailsRoutes
import com.sultan.pepulesaat.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(AuthScreen.SignOut.route)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                Box(modifier = Modifier.height(56.dp))
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .padding(values)
                    .padding(8.dp)

            ) {
                Text(
                    text = "Products On Sale",
                    style = Typography.bodySmall,
                    fontWeight = FontWeight.Light
                    )
                state.saleProducts.let {
                        products ->
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .height(100.dp)
                            .padding(8.dp),
                        rows = GridCells.Fixed(1)
                    ) {
                        items(products) { product ->
                            SaleProductItem(
                                productDTO = product,
                                onClick = {
                                    navController.navigate(DetailsRoutes.ProductDetails.route + "/${product.id}")
                                }
                            )
                        }
                    }
                }
                Divider()
                state.products.let {
                    LazyVerticalGrid(
                        modifier = Modifier,
                        columns = GridCells.Fixed(2)
                    ) {
                        items(it) { product ->
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