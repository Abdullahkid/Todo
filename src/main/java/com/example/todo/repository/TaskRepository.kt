package com.example.todo.repository

import com.example.todo.data.TaskDatabaseDao
import com.example.todo.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDatabaseDao: TaskDatabaseDao){
    suspend fun addTask(task : Task)= taskDatabaseDao.insert(task)
    suspend fun updateTask(task : Task)= taskDatabaseDao.update(task)
    suspend fun deleteTask(task: Task)= taskDatabaseDao.deleteTask(task)
    suspend fun deleteAllTasks() = taskDatabaseDao.deleteAll()
    fun getAllTask(): kotlinx.coroutines.flow.Flow<List<Task>> = taskDatabaseDao.getTask()
        .flowOn(Dispatchers.IO).conflate()
}