package com.mohit.scanrider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ImageSelectorBottomSheet(
    onGallerySelect: () -> Unit,
    onCameraSelect: () -> Unit,
    onDismiss: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Select Image", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    onGallerySelect()
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Choose from Gallery")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    onCameraSelect()
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Capture from Camera")
            }
        }
    }
}
