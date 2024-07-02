package com.example.todolist.db

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val allItems: Flow<List<TodoItem>> = todoDao.getAllItems()

    suspend fun insert(item: TodoItem) {
        todoDao.insert(item)
    }

    fun searchItems(search: String): Flow<List<TodoItem>> {
        return todoDao.searchItems(search)
    }
}
