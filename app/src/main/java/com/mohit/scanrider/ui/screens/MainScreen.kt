package com.mohit.scanrider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.mohit.scanrider.data.model.Vehicle
import com.mohit.scanrider.ui.theme.ScanRiderTheme

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit,
    onSearch: (String) -> Unit,
    vehicleData: List<Vehicle> // Assuming Vehicle is a data class for vehicle information
) {
    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Scan Rider", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.Outlined.Delete, contentDescription = "Logout")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                // Search Bar
                var searchText by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Search Vehicle") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Horizontal Pager for Swipeable Vehicle Cards
                if (vehicleData.isNotEmpty()) {
                    HorizontalPager(
                        state = pagerState,
                        count = vehicleData.size,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp) // Adjust height as needed
                    ) { page ->
                        VehicleCard(vehicle = vehicleData[page])
                    }
                } else {
                    // Placeholder for no data
                    Text(
                        text = "Try searching for a vehicle",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Gray
                    )
                }
            }
        }
    )
}

@Composable
fun VehicleCard(vehicle: Vehicle) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = vehicle.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "${vehicle.year} - ${vehicle.model}", color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Auction Price: ${vehicle.auctionPrice}", fontSize = 16.sp)
            Text(text = "Shipping Cost: ${vehicle.shippingCost}", fontSize = 16.sp)
        }
    }
}
