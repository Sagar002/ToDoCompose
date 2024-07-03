package com.example.todolist.data.repository

import com.example.todolist.data.db.model.TodoItem
import com.example.todolist.data.db.TodoDao
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) : TodoRepository {

    //val allItems: Flow<List<TodoItem>> = todoDao.getAllItems()

    override suspend fun getAllItems():Flow<List<TodoItem>> {
       return todoDao.getAllItems()
    }
    override suspend fun insert(item: TodoItem) {
        todoDao.insert(item)
    }

    override suspend fun searchItems(search: String): Flow<List<TodoItem>> {
        return todoDao.searchItems(search)
    }
}
