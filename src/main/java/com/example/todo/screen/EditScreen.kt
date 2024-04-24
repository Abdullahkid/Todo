package com.example.todo.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todo.model.Task

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EditScreen(
    id: Int,
    navController: NavController,
    task: String,
    onUpdateTask: (Task)-> Unit
){
    val keyboardController = LocalSoftwareKeyboardController.current
    val onImeAction: () -> Unit = {}
    var taskEdit by remember{
        mutableStateOf(task)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(it)) {
            Row{
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable {
                            navController.navigateUp()
                        }
                        .size(40.dp)
                )
                Text(
                    text = "Task",
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            val textState = remember { mutableStateOf(TextFieldValue()) }
            val scrollState = rememberScrollState()

            TextField(
                value = taskEdit,
                onValueChange = {valueEntered->
                    taskEdit = valueEntered },
                textStyle = TextStyle( fontSize = 20.sp),
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxSize(2f)
                    .verticalScroll(scrollState),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onUpdateTask(
                            Task(
                                task = taskEdit,
                                id = id
                            )
                        )
                        keyboardController?.hide()
                        navController.navigateUp()
                    }
                )
            )
        }
    }
}

