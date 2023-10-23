package com.sultan.pepulesaat.presentation.ui.auth.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.sultan.pepulesaat.presentation.navigation.BottomBarScreen
import com.sultan.pepulesaat.presentation.navigation.graphs.AuthScreen
import com.sultan.pepulesaat.presentation.navigation.graphs.Graph
import com.sultan.pepulesaat.presentation.ui.auth.AuthEvent
import com.sultan.pepulesaat.presentation.ui.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            navController.navigate(Graph.HOME) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Let's Sign You In.",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))

        ClickableText(
            text = AnnotatedString(text = "I don't have account"),
            onClick = { navController.navigate(AuthScreen.SignUp.route) }
        )

        Button(
            onClick = {
                viewModel.onEvent(AuthEvent.SignIn(email, password))
                if (state.isSignInSuccessful) {
                    navController.navigate(BottomBarScreen.FeedScreen.route)
                } else {
                    scope.launch {
                        snackbarHostState
                            .showSnackbar(
                                "Couldn't Sign In. Please Check Your Internet Connection"
                            )
                    }
                }
            },
            enabled = email.isNotEmpty() && password.length > 6
        ) {
            Text(text = "Sign In")
        }
    }
}

/*
@Composable
@Preview(showBackground = true)
fun SignInPreview() {
    SignInScreen(
        viewModel = AuthViewModel(
            FirebaseAuth.getInstance(),
            LocalContext.current.applicationContext
        )
    )
}*/
