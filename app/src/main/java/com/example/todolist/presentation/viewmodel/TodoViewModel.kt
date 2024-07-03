package com.example.todolist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.db.TodoDao
import com.example.todolist.data.db.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {


    private val _todoItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoItems: StateFlow<List<TodoItem>> = _todoItems
    /*init {
        viewModelScope.launch {
            repository.getAllItems().collect {
                _todoItems.value = it
            }
        }
    }*/

    fun insert(item: TodoItem) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun searchTodoItems(search: String) {
        viewModelScope.launch {
            repository.searchItems(search).collect {
                _todoItems.value = it
            }
        }
    }
    fun getAll() {
        viewModelScope.launch {
            repository.getAllItems().collect {
                _todoItems.value = it
            }
        }
    }

}