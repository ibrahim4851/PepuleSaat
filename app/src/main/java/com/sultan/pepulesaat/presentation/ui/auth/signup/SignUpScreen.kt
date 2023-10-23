package com.sultan.pepulesaat.presentation.ui.auth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sultan.pepulesaat.presentation.navigation.graphs.AuthScreen
import com.sultan.pepulesaat.presentation.ui.auth.AuthEvent
import com.sultan.pepulesaat.presentation.ui.auth.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirmation by rememberSaveable { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                isEmailValid = validateEmail(email)
            },
            placeholder = {
                Text(text = "Email")
            },
            supportingText = {
                if (!isEmailValid) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Email is not valid",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = { passwordConfirmation = it },
            placeholder = {
                Text(text = "Password Again")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validatePasswords(password, passwordConfirmation)) {
                    viewModel.onEvent(AuthEvent.SignUp(email, password))
                    navController.navigate(AuthScreen.SignIn.route)
                } else {
                    scope.launch {
                        snackbarHostState
                            .showSnackbar(
                                "Password should be longer than 6 characters")
                    }
                }
            }
        )
        {
            Text(text = "Sign Up")
        }
    }

}

fun validatePasswords(password: String, confirmPassword: String): Boolean {
    if (password.length < 6 || confirmPassword.length < 6) {
        return false
    }

    if (password != confirmPassword) {
        return false
    }

    return true
}

fun validateEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    if (email.length > 7 && email.matches(emailRegex.toRegex())) {
        return true
    }
    return false
}

@Preview(showSystemUi = true)
@Composable
fun SignUpPreview() {
    SignUpScreen(
        navController = rememberNavController()
    )
}