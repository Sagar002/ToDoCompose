package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.db.TodoDatabase
import com.example.todolist.db.TodoItem
import com.example.todolist.db.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository

    private val _todoItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoItems: StateFlow<List<TodoItem>> = _todoItems
    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        viewModelScope.launch {
            repository.allItems.collect {
                _todoItems.value = it
            }
        }
    }

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
            repository.allItems.collect {
                _todoItems.value = it
            }
        }
    }

}