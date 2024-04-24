package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.navigation.TaskNavigation
import com.example.todo.screen.TaskScreen
import com.example.todo.screen.TaskViewModel
import com.example.todo.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val taskViewModel : TaskViewModel by viewModels()
//                    TaskApp(taskViewModel = taskViewModel)
                    TaskNavigation(taskViewModel = taskViewModel)
                }
            }
        }
    }
}

//@Composable
//fun TaskApp(taskViewModel: TaskViewModel){
//    val taskList = taskViewModel.taskList.collectAsState().value
//    TaskScreen(
//        TaskList =taskList,
//        onAddTask ={
//            taskViewModel.addTask(it)
//        },
//        onDeleteTask ={
//            taskViewModel.deleteTask(it)
//        }
//    )
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoTheme {
        //Greeting("Android")
    }
}