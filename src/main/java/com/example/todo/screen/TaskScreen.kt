package com.example.todo.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.model.Task
import com.example.todo.navigation.NavigationScreen
import com.example.todo.utils.formatDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TaskScreen(
    navController: NavController,
    TaskList : List<Task>,
    onAddTask: (Task)-> Unit,
    onDeleteTask: (Task)-> Unit,
){
    var showDialog by remember{
        mutableStateOf(false)
    }
    var taskField by remember{
        mutableStateOf(" ")
    }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val onImeAction: () -> Unit = {}
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tasks" )
                },
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState(),
                    canScroll = { true }),
                colors = TopAppBarDefaults.smallTopAppBarColors(Color.Gray)
            )
        } ,
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )
            }
        },
    ) { padding->
        if (showDialog){
            AlertDialog(
                modifier = Modifier.padding(padding),
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Add Task")},
                text = {

                    TextField(
                        value = taskField,
                        onValueChange = {
                            taskField = it
                        },
                        maxLines = 3,
                        label = { Text(text = "Task")},
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onImeAction()
                                keyboardController?.hide()
                            }
                        )
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            onAddTask(
                                Task(
                                    task = taskField
                                )
                            )
                            taskField = ""
                            Toast.makeText(
                                context,
                                "Task Added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Text(text = "Add")
                    }
                }
            )
        }
        LazyColumn(modifier = Modifier.padding(top = 65.dp)){
            items(TaskList){Task->
                TaskList(
                    task =  Task,
                    navController= navController,
                    onTaskClicked = {
                        onDeleteTask(Task)
                    }
                )
            }
        }
    }
}

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    task: Task,
    navController: NavController,
    onTaskClicked: (Task) -> Unit
){
    var tick by remember {
        mutableStateOf(false)
    }
    Surface(
        Modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(16.dp)
            )
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.clickable {
                navController.navigate(route = NavigationScreen.DetailScreen.name + "/${task.task}/${task.id}")
            }
        ) {
            Column(modifier = Modifier.clickable {
                tick = !tick
            }
                .width(55.dp)
                .align(Alignment.CenterVertically)
                .padding(vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Icon(
                    imageVector = if (tick){
                        Icons.Default.CheckCircle
                    } else Icons.Outlined.Circle,
                    contentDescription = "Circle Icon",
                )
            }
            Column(modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(vertical = 6.dp)) {
                Text(text = task.task,
                    style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = formatDate(task.entryDate.time),
                    style = MaterialTheme.typography.labelMedium)
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(55.dp)
                    .padding(vertical = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector =Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier= Modifier.clickable {
                        onTaskClicked(task)
                    }
                )
            }
        }
    }
}
