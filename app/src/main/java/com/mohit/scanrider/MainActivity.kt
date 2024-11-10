package com.mohit.scanrider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohit.scanrider.ui.screens.AuthScreen
import com.mohit.scanrider.ui.screens.MainScreen
import com.mohit.scanrider.ui.theme.ScanRiderTheme
import com.mohit.scanrider.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScanRiderTheme {
                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel()

                // Observe authentication state
                val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = if (isAuthenticated) "main" else "auth"
                ) {
                    composable("auth") {
                        AuthScreen(
                            isLogin = true,
                            onAuthAction = { email, password ->
                                authViewModel.signInWithEmail(email, password) // Firebase email login
                            },
                            onGoogleSignIn = { authViewModel.signInWithGoogle(this@MainActivity) }, // Google Sign-In
                            onToggleMode = {
                                authViewModel.toggleLoginMode() // Toggle between login and registration
                            }
                        )
                    }
                    composable("main") {
                        MainScreen(
                            onLogout = { authViewModel.logout() },
                            onSearch = TODO(),
                            vehicleData = TODO(),
//                            onVehicleSearch = { /* Implement vehicle search */ },
//                            onImageSelectorOpen = { /* Implement image selector */ }
                        )
                    }
                }
            }
        }
    }
}
