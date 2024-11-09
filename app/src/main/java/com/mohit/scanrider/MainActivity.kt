package com.mohit.scanrider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import com.mohit.scanrider.ui.theme.ScanRiderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase here
        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
        setContent {
            ScanRiderTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            // Add your main screen content here
            Text(
                text = "Welcome to Scan Rider!",
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ScanRiderTheme {
        MainScreen()
    }
}
