package com.example.todolist.presentation.view
sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object EnterNewItemScreen : Screen("enter_new_item_screen")
}