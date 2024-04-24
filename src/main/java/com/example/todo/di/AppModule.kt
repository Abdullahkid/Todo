package com.example.todo.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.data.TaskDatabase
import com.example.todo.data.TaskDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDatabaseDao =
        taskDatabase.taskDao()

    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context):TaskDatabase=
        Room.databaseBuilder(
            context = context,
            klass = TaskDatabase::class.java,
            name = "task_db"
        ).fallbackToDestructiveMigration().build()
}

