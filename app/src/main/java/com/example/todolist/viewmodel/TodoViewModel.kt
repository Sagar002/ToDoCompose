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
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository

    val allItems: LiveData<List<TodoItem>>

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allItems = repository.allItems.asLiveData()
    }

    fun insert(item: TodoItem) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun searchItems(search: String): LiveData<List<TodoItem>> {
        return repository.searchItems(search).asLiveData()
    }
}