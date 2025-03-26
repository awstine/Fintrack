package com.micahnyabuto.fintrack.ui.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title :String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val items= listOf(
    BottomNavigationItem(
        title = "",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Filled.Home,
    ),
    BottomNavigationItem(
        title = "",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Filled.Person,
    ),

    BottomNavigationItem(
        title = "",
        selectedIcon = Icons.Filled.Add,
        unselectedIcon = Icons.Filled.Add,
        //shape = circleshape
    ),

    BottomNavigationItem(
        title = "",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Filled.Person,
    ),

    BottomNavigationItem(
        title = "",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Filled.Person,
    ),
)
