package com.example.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import kotlin.random.Random

@Entity(tableName = "task_tbl")
data class Task(
    @PrimaryKey
    val id : Int = Random.nextInt(),
    @ColumnInfo(name = "task_info")
    val task : String,
    @ColumnInfo(name = "task_entry_date")
    val entryDate: Date = Date.from(Instant.now())
)
