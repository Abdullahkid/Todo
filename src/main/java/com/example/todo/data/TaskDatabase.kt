package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.model.Task
import com.example.todo.utils.DateConverter
import com.example.todo.utils.UUIDConverter

@Database(entities = [Task::class], version = 5, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao():TaskDatabaseDao
}