package com.example.todo.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.model.Task
import com.example.todo.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {
    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList = _taskList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.getAllTask().distinctUntilChanged().collect(){listOfTasks->
                if (listOfTasks.isNullOrEmpty()){
                    Log.d("Empty", "Empty List")
                }else{
                    _taskList.value = listOfTasks
                }
            }
        }
    }

    fun addTask(task: Task)= viewModelScope.launch{ taskRepository.addTask(task) }
    fun updateTask(task: Task)= viewModelScope.launch{ taskRepository.updateTask(task) }
    fun deleteTask(task: Task)= viewModelScope.launch{ taskRepository.deleteTask(task) }
}