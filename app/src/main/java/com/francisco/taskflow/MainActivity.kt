package com.francisco.taskflow

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

import com.francisco.taskflow.data.*
import com.francisco.taskflow.repository.*
import com.francisco.taskflow.ui.navigation.*
import com.francisco.taskflow.ui.theme.*
import com.francisco.taskflow.viewmodel.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.hide()

        setContent {
            val context = LocalContext.current
            val dao = TaskDatabase.getDatabase(context).taskDao()
            val repository = TaskRepository(dao)

            val viewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(repository)
            )

            TaskFlowTheme {
                NavGraph(viewModel)
            }
        }
    }
}