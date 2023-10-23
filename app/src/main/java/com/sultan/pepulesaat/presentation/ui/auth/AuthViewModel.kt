package com.sultan.pepulesaat.presentation.ui.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @ApplicationContext private val context: Context): ViewModel() {

    private val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    private val _state = mutableStateOf(AuthState())
    val state : State<AuthState> = _state

    private fun performSignIn(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _state.value = AuthState(isSignInSuccessful = true)
                    editor.putString("userId", firebaseAuth.currentUser!!.uid)
                    editor.apply()
                } else {
                    _state.value = AuthState(isSignInSuccessful = false)
                }
            }
        }
        catch (exception: FirebaseAuthException){
            Log.e("FirebaseError", exception.errorCode)
        }
    }

    private fun performSignUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _state.value = AuthState(isSignUpSuccessful = true)
                } else {
                    _state.value = AuthState(isSignUpSuccessful = false)
                }
            }
    }

    private fun performSignOut() {
        try {
            firebaseAuth.signOut()
            val editor = sharedPreferences.edit()
            editor.putString("userId", null)
            editor.apply()

            _state.value = AuthState(isSignOutSuccessful = true)
        } catch (exception: FirebaseAuthException) {
            Log.e("FirebaseError", exception.errorCode)
        }
    }


    fun onEvent(event: AuthEvent) {
        when(event) {
            is AuthEvent.SignIn -> {
                performSignIn(event.email, event.password)
            }
            is AuthEvent.SignUp -> {
                performSignUp(event.email, event.password)
            }
            is AuthEvent.SignOut -> {
                performSignOut()
            }
        }
    }

}