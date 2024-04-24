package com.example.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.screen.EditScreen
import com.example.todo.screen.TaskScreen
import com.example.todo.screen.TaskViewModel

@Composable
fun TaskNavigation(taskViewModel: TaskViewModel){
    val taskList= taskViewModel.taskList.collectAsState().value

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.HomeScreen.name
    ){
        composable(NavigationScreen.HomeScreen.name){
            TaskScreen(
                navController= navController,
                TaskList = taskList,
                onAddTask = {
                     taskViewModel.addTask(it)
                },
                onDeleteTask ={
                    taskViewModel.deleteTask(it)
                }
            )
        }
        composable(NavigationScreen.DetailScreen.name + "/{task}/{id}",
        arguments = listOf(navArgument(name = "task"){type = NavType.StringType},
            navArgument(name = "id"){type = NavType.IntType}
        )){backStackEntry ->
            val task = backStackEntry.arguments?.getString("task")
            val id = backStackEntry.arguments?.getInt("id")
            EditScreen(
                navController = navController,
                task = task!!,
                id = id!!,
                onUpdateTask = {
                    taskViewModel.updateTask(it)
                }
            )
        }
    }
}