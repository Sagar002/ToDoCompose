package com.example.todolist.domain.repository

import com.example.todolist.data.db.model.TodoItem
import kotlinx.coroutines.flow.Flow

public interface TodoRepository {
    suspend fun insert(item: TodoItem)
    suspend fun searchItems(search: String): Flow<List<TodoItem>>
    suspend fun getAllItems():Flow<List<TodoItem>>
}