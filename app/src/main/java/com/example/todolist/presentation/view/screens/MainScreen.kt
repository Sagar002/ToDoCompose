package com.example.todolist.presentation.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.data.db.model.TodoItem
import com.example.todolist.presentation.view.composable.AddTodoLabelUI
import com.example.todolist.presentation.view.composable.TodoItemUI
import com.example.todolist.presentation.viewmodel.TodoViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    todoViewModel: TodoViewModel, navigateToAddItem: () -> Unit
) {
    val todoItems by todoViewModel.todoItems.collectAsState(emptyList())
    var searchText by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(Unit) {
        delay(200)
        if (searchText.isEmpty()) todoViewModel.getAll()
        else todoViewModel.searchTodoItems(searchText)
    }

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text("Auto hide or Extend FAB")
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navigateToAddItem()
        }, content = { Icon(Icons.Default.Add, contentDescription = "Add") })
    }) {
        if (searchText.isEmpty() && todoItems.isEmpty()) {
            AddTodoLabelUI()
        } else {
            MainScreenContent(todoItems = todoItems) {
                searchText = it
                if (it.isNotEmpty()) {
                    todoViewModel.searchTodoItems(it)
                } else {
                    todoViewModel.getAll()
                }
            }
        }
    }
}


@Composable
fun MainScreenContent(todoItems: List<TodoItem>, onTextChange: (String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 20.dp, end = 20.dp)
    ) {
        OutlinedTextField(value = searchText, onValueChange = {
            searchText = it
            onTextChange(searchText)
        }, label = { Text(text = "Search item") }, modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(items = todoItems) {
                TodoItemUI(text = it.title)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    TodoItemUI("this is item")
}

