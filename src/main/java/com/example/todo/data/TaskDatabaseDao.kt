package com.example.todo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDatabaseDao {

    @Query("SELECT * from task_tbl")
    fun getTask(): Flow<List<Task>>

    @Query("SELECT * from task_tbl where id =:id")
    suspend fun getTaskById(id : String):Task

    @Update
    suspend fun update(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("DELETE from task_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteTask(task: Task)
}
