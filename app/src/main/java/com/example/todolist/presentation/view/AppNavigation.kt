package com.example.todolist.presentation.view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.presentation.viewmodel.TodoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                //todoViewModel = viewModel(),
                todoViewModel =  hiltViewModel<TodoViewModel>(),
                navigateToAddItem = { navController.navigate(Screen.EnterNewItemScreen.route) }
            )
        }
        composable(Screen.EnterNewItemScreen.route) {
            EnterNewItemScreen(
                todoViewModel = hiltViewModel<TodoViewModel>(),
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}