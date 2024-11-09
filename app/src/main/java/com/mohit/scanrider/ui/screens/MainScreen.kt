package com.mohit.scanrider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var showBottomSheet by remember { mutableStateOf(false) }

    ModalBottomSheet(
        sheetContent = {
            ImageSelectorBottomSheet(
                onGallerySelect = { /* TODO: Handle gallery selection */ },
                onCameraSelect = { /* TODO: Handle camera selection */ },
                onDismiss = { showBottomSheet = false }
            )
        },
        sheetState = bottomSheetState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Scan Rider") },
                    actions = {
                        IconButton(onClick = { /* TODO: Handle logout */ }) {
                            Icon(Icons.Filled.Logout, contentDescription = "Logout")
                        }
                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Welcome to Scan Rider!")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        showBottomSheet = true
                        scope.launch { bottomSheetState.show() }
                    }) {
                        Text("Select Image")
                    }
                }
            }
        )
    }

    if (showBottomSheet) {
        LaunchedEffect(Unit) {
            scope.launch { bottomSheetState.show() }
        }
    }
}
