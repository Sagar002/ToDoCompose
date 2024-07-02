package com.example.todolist.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                todoViewModel = viewModel(),
                navigateToAddItem = { navController.navigate(Screen.EnterNewItemScreen.route) }
            )
        }
        composable(Screen.EnterNewItemScreen.route) {
            EnterNewItemScreen(
                todoViewModel = viewModel(),
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}