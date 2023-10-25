package com.sultan.pepulesaat.presentation.ui.auth.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sultan.pepulesaat.R
import com.sultan.pepulesaat.presentation.navigation.graphs.AuthScreen
import com.sultan.pepulesaat.presentation.ui.auth.AuthEvent
import com.sultan.pepulesaat.presentation.ui.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirmation by rememberSaveable { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 30.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Let us Introduce You to Best Watches in The World",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge
            )
        }
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
                if (!isEmailValid && email.length > 10) {
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
            label = { Text("Password") },
            placeholder = {
                Text(text = "Password")
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.baseline_visibility)
                else painterResource(id = R.drawable.baseline_visibility_off)

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, description)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordConfirmation,
            onValueChange = { passwordConfirmation = it },
            label = { Text("Password Confirmation") },
            placeholder = {
                Text(text = "Password Again")
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.baseline_visibility)
                else painterResource(id = R.drawable.baseline_visibility_off)

                // Please provide localized description for accessibility services
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, description)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(7f),
                onClick = {
                    if (validatePasswords(password, passwordConfirmation)) {
                        viewModel.onEvent(AuthEvent.SignUp(email, password))
                        navController.navigate(AuthScreen.SignIn.route)
                    } else if (!validatePasswords(password, passwordConfirmation)) {
                        Toast.makeText(
                            context,
                            "Passwords aren't matching",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Couldn't Sign In. Please Check Your Internet Connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            )
            {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.weight(3f))
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