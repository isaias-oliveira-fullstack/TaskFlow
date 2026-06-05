package com.francisco.taskflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.francisco.taskflow.ui.components.*
import com.francisco.taskflow.viewmodel.TaskViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedTasksScreen(
    viewModel: TaskViewModel,
    onBack: () -> Unit
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())
    val completedTasks = tasks.filter { it.isDone }

    var isLoading by remember { mutableStateOf(true) }

    val count = tasks.count { it.isDone }

    val title = when (count) {
        1 -> "Tarefa Concluída"
        else -> "Tarefas Concluídas"
    }

    val total = when (count) {
        1 -> "Total de 1 tarefa"
        else -> "Total de $count tarefas"
    }

    LaunchedEffect(Unit) {
        delay(2000)
        isLoading = false
    }

    if (isLoading) {
        Loading()
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),

        topBar = {
            if (!isLoading && completedTasks.isNotEmpty()) {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Column {
                            Text(
                                text = title,
                                color = MaterialTheme.colorScheme.surface,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Voltar",
                                modifier = Modifier.size(28.dp),
                                tint = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                )
            }
        }
    ) { padding ->

        if (!isLoading && completedTasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Nenhuma Tarefa Concluída",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Você ainda não concluiu nenhuma tarefa.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onBack,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Voltar para Minhas Tarefas",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(
                    horizontal = 8.dp
                )
            ) {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 8.dp,
                                top = 12.dp,
                                end = 8.dp,
                                bottom = 5.dp
                            )
                    ) {
                        if (count > 0) {
                            Text(
                                text = total,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }

                items(completedTasks) { task ->
                    TaskItem(
                        task = task,
                        onDelete = { viewModel.deleteTask(task) },
                        onToggle = { viewModel.toggleTask(task) }
                    )
                }
            }
        }
    }
}