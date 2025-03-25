package com.micahnyabuto.fintrack.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun FintrackApp(){
    val items= listOf(
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Filled.Home,
        ),
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.BarChart,
            unselectedIcon = Icons.Filled.BarChart,

            ),

        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Filled.Add,
            //shape = circleshape

        ),
        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Wallet,
            unselectedIcon = Icons.Filled.Wallet,

            ),

        BottomNavigationItem(
            title = "",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Filled.Person,

            ),



        )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index,item ->
                    NavigationBarItem(
                        selected = selectedItemIndex==index,
                        onClick = {
                            selectedItemIndex= index
                        },
                        label = {
                            Text(text= item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if(index== selectedItemIndex){
                                    item.selectedIcon
                                }else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )

                }

            }
        }
    ) {  }
}