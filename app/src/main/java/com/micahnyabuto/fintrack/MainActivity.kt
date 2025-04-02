package com.micahnyabuto.fintrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose.FintrackTheme
import com.micahnyabuto.fintrack.ui.screens.home.FintrackApp
import com.micahnyabuto.fintrack.ui.screens.statistics.StatisticsScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FintrackTheme {
//                FintrackApp()
                StatisticsScreen()
            }
        }
    }
}