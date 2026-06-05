package com.francisco.taskflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.francisco.taskflow.ui.components.*
import com.francisco.taskflow.viewmodel.TaskViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onNavigateCompleted: () -> Unit
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    var isLoading by remember { mutableStateOf(true) }

    val count = tasks.count()

    val title = when (count) {
        1 -> "Minha Tarefa"
        else -> "Minhas Tarefas"
    }

    val completedTitle = when (val count = tasks.count { it.isDone }) {
        1 -> "Ver Concluída (1)"
        else -> "Ver Concluídas ($count)"
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
            if (!isLoading && tasks.isNotEmpty()) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.surface,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    actions = {
                        if (tasks.any { it.isDone }) {
                            TextButton(onClick = onNavigateCompleted) {
                                Text(
                                    text = completedTitle,
                                    color = MaterialTheme.colorScheme.surface,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (!isLoading && tasks.isNotEmpty()) {
                FloatingActionButton(
                    onClick = onAddClick,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Add,
                        contentDescription = "Adicionar tarefa",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    ) { padding ->

        if (!isLoading && tasks.isEmpty()) {
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
                        text = "Nenhuma Tarefa Ainda",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Crie sua primeira tarefa para começar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onAddClick,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Adicionar Primeira Tarefa",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding ),
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

                items(tasks) { task ->
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