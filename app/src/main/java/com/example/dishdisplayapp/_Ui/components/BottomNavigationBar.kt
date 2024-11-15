package com.example.dishdisplayapp._Ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BottomNavigationBar(
    selectedTab: BottomNavTab,
    onTabSelected: (BottomNavTab) -> Unit
) {
    BottomAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 8.dp,
        modifier = Modifier
            .height(120.dp)
            .navigationBarsPadding()
    ) {
        BottomNavTab.values().forEach { tab ->
            BottomNavigationItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.label,
                            tint = if (tab == selectedTab) Color(0xFFFF5722) else Color(0xFF1E88E5),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = tab.label,
                            style = MaterialTheme.typography.caption,
                            fontSize = 11.sp
                        )
                    }
                },
                selectedContentColor = Color(0xFFFF5722),
                unselectedContentColor = Color.Gray,
                alwaysShowLabel = true
            )
        }
    }
}

enum class BottomNavTab(val label: String, val icon: ImageVector) {
    Cook("Cook", Icons.Default.Restaurant),
    Favourite("Favourites", Icons.Default.Favorite),
    Manual("Manual", Icons.Default.MenuBook),
    Device("Device", Icons.Default.Devices),
    Preferences("Preferences", Icons.Default.Person),
    Settings("Settings", Icons.Default.Settings)
}



