package com.al4apps.binlib.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.al4apps.binlib.presentation.history.HistoryScreen
import com.al4apps.binlib.presentation.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home.route) {
        composable(AppScreens.Home.route) {
            HomeScreen(navController)
        }
        composable(AppScreens.History.route) {
            HistoryScreen(navController)
        }
    }
}

sealed class AppScreens(val route: String) {
    data object Home : AppScreens(route = "home")
    data object History : AppScreens(route = "history")
}