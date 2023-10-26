package com.sultan.pepulesaat.presentation.ui.cart

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sultan.pepulesaat.R
import com.sultan.pepulesaat.presentation.navigation.graphs.Graph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(state.isRemoveSuccessful) {
        if (state.isRemoveSuccessful) {
            Toast.makeText(context, state.removedMessage, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = "Cart")
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.onEvent(
                            CartEvent.ClearCart("")
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Graph.CART_ORDER) },
                icon = {
                    Icon(
                        painterResource(
                            id = R.drawable.baseline_shopping_cart_checkout
                        ),
                        ""
                    )
                },
                text = { Text(text = "Checkout") },
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                state.products.let {
                    LazyVerticalGrid(
                        contentPadding = innerPadding,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        columns = GridCells.Fixed(1)
                    ) {
                        items(it) { product ->
                            CartProductItem(
                                productDTO = product,
                                onRemoveClick = {
                                    viewModel.onEvent(
                                        CartEvent.RemoveFromCart(product.id)
                                    )
                                }
                            )
                        }
                    }

                }
            }
        }
    )
}