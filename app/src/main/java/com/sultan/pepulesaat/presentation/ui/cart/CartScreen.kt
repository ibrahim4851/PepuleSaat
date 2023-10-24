package com.sultan.pepulesaat.presentation.ui.cart

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sultan.pepulesaat.presentation.navigation.BottomBar
import com.sultan.pepulesaat.presentation.navigation.graphs.CartOrderRoutes
import com.sultan.pepulesaat.presentation.navigation.graphs.DetailsRoutes
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
                        navController.navigate(Graph.CART_ORDER)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
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
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(intrinsicSize = IntrinsicSize.Max)) {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Proceed Order")
                        }
                    }
                }
            }
        }
    )
}