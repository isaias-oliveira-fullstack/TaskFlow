package com.francisco.taskflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.francisco.taskflow.ui.screens.*
import com.francisco.taskflow.viewmodel.TaskViewModel

@Composable
fun NavGraph(viewModel: TaskViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate("add") },

                onNavigateCompleted = {
                    navController.navigate("completed")
                }
            )
        }

        composable("add") {
            AddTaskScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("completed") {
            CompletedTasksScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}