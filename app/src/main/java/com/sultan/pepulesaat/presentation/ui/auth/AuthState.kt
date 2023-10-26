package com.sultan.pepulesaat.presentation.ui.auth

data class AuthState(
    val isSignUpSuccessful: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val isSignOutSuccessful: Boolean = false,
    val isLoading: Boolean = true
)
