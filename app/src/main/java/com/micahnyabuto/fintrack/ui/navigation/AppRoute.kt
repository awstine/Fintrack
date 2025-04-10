package com.micahnyabuto.fintrack.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.micahnyabuto.fintrack.ui.screens.expense.AddExpenseScreen
import com.micahnyabuto.fintrack.ui.screens.home.FintrackApp
import com.micahnyabuto.fintrack.ui.screens.onboarding.OnboardingScreen
import com.micahnyabuto.fintrack.ui.screens.statistics.StatisticsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppRoute(startDestination: String = Destinations.ONBOARDING) {
    val navController = rememberNavController()
    // Navigation logic goes here
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Destinations.ONBOARDING) {
            OnboardingScreen(
                onGetStartedClick = {
                    navController.navigate(Destinations.FINTRACK) {
                        // Clear the back stack to prevent going back to the onboarding screen
                        popUpTo(Destinations.ONBOARDING) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(route = Destinations.FINTRACK) {
            FintrackApp(
                navigateToWallet = {
                    navController.navigate(Destinations.WALLET_SCREEN)
                },
                navigateToChart = {
                    navController.navigate(Destinations.STATISTICS_SCREEN)
                },
                navigateToProfile = {
//                    navController.navigate(Destinations.PROFILE_SCREEN)
                },
                navigateToAdd = {
                    navController.navigate(Destinations.ADD_EXPENSE_SCREEN)
                },
            )
        }

        composable(route = Destinations.WALLET_SCREEN) {
            // WalletScreen()
        }

        composable(route = Destinations.ADD_EXPENSE_SCREEN) {
            AddExpenseScreen(
                onNavigateBack = {
                    navController.navigate(Destinations.FINTRACK) {
                        // Clear the back stack to prevent going back to the add expense screen
                        popUpTo(Destinations.FINTRACK) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(route = Destinations.STATISTICS_SCREEN) {
            StatisticsScreen()
        }
        composable(route = Destinations.PROFILE_SCREEN) {
            // ProfileScreen()
        }
    }
}
