package com.micahnyabuto.fintrack.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title :String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
