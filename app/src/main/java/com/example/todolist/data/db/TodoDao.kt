package com.example.todolist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todolist.data.db.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(item: TodoItem)

    @Query("SELECT * FROM todo_items")
    fun getAllItems(): Flow<List<TodoItem>>

    // Optional: Query with search filter
    @Query("SELECT * FROM todo_items WHERE title LIKE '%' || :search || '%'")
    fun searchItems(search: String): Flow<List<TodoItem>>
}