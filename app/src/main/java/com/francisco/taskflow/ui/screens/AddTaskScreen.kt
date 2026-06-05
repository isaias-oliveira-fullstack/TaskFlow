package com.francisco.taskflow.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.francisco.taskflow.ui.components.Loading
import com.francisco.taskflow.viewmodel.TaskViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel,
    onBack: () -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(true) }

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
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = "Adicionar Tarefa",
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.surface,
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            TextField(
                value = title,
                onValueChange = {
                    title = it
                    showError = false
                },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth(),
                isError = showError && title.isBlank()
            )

            TextField(
                value = description,
                onValueChange = {
                    description = it
                    showError = false
                },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth(),
                isError = showError && description.isBlank(),
                minLines = 5,
                maxLines = 10
            )

            if (showError) {
                Text(
                    text = "Preencha todos os campos",
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(0.dp))

            Button(
                onClick = {

                    if (title.isBlank() || description.isBlank()) {
                        showError = true
                        return@Button
                    }

                    viewModel.addTask(title, description)
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Salvar Tarefa",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}