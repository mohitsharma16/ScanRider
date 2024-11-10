package com.mohit.scanrider.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Tracks whether the user is authenticated
    private val _isAuthenticated = MutableStateFlow(auth.currentUser != null)
    val isAuthenticated: StateFlow<Boolean> get() = _isAuthenticated

    // Email/password login
    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _isAuthenticated.value = true
                    } else {
                        // Handle failure
                    }
                }
        }
    }

    // Email/password registration
    fun registerWithEmail(email: String, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _isAuthenticated.value = true
                    } else {
                        // Handle failure
                    }
                }
        }
    }

    // Google Sign-In
    fun signInWithGoogle(activity: Activity) {
        // TODO: Implement Google Sign-In logic
        // This will require a GoogleSignInClient and handling Google sign-in intents
    }

    // Logout
    fun logout() {
        auth.signOut()
        _isAuthenticated.value = false
    }

    // Toggle login/registration mode
    fun toggleLoginMode() {
        // Toggle between login and registration mode
    }
}
