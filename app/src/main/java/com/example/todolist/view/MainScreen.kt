package com.example.todolist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.db.TodoItem
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    todoViewModel: TodoViewModel,
    navigateToAddItem: () -> Unit
) {
    val todoItems by todoViewModel.todoItems.collectAsState(emptyList())
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Auto hide or Extend FAB")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddItem() },
                content = { Icon(Icons.Default.Add, contentDescription = "Add") }
            )
        }
    ) {
        if (todoItems.isEmpty()) {
            CenteredText()
        } else {
            MainScreenContent(todoItems = todoItems) {
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
fun CenteredText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Press the + button to add a TODO item",
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 110.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCenteredText() {
    CenteredText()
}

@Composable
fun MainScreenContent(todoItems: List<TodoItem>, onTextChange: (String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 20.dp, end = 20.dp)
    ) {
        //not implemented
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onTextChange(searchText)
            },
            label = { Text(text = "Search item") },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(items = todoItems) {
                ListItem(text = it.title)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun itemPreview() {
    ListItem("this is item")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(text: String) {
    Card(
        onClick = { },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.padding(top = 3.dp, bottom = 3.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 50.dp)
                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
        )
    }
}