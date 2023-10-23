package com.sultan.pepulesaat.presentation.ui.auth

sealed class AuthEvent {
    data class SignIn(val email: String, val password: String) : AuthEvent()
    data class SignUp(val email: String, val password: String) : AuthEvent()
    data class SignOut(val email: String) : AuthEvent()
}
