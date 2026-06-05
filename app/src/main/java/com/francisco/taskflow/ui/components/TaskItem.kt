package com.francisco.taskflow.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import com.francisco.taskflow.data.Task

fun String.limitText(max: Int): String {
    if (length <= max) return this

    val cut = take(max).trim()

    val lastSpace = cut.lastIndexOf(" ")

    val finalText = if (lastSpace > 0) {
        cut.substring(0, lastSpace)
    } else {
        cut
    }

    return "$finalText..."
}
@Composable
fun TaskItem(
    task: Task,
    onDelete: () -> Unit,
    onToggle: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    var showDialogDetail by remember { mutableStateOf(false) }

    val textColor = if (task.isDone)
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
    else
        MaterialTheme.colorScheme.onSurface

    val textDecoration = if (task.isDone)
        TextDecoration.LineThrough
    else
        null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onToggle) {
                Icon(
                    imageVector = if (task.isDone)
                        Icons.Default.CheckCircle
                    else
                        Icons.Default.RadioButtonUnchecked,
                    contentDescription = "Concluir tarefa",
                    tint = if (task.isDone)
                        Color(0xFF2E7D32)
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = task.title.limitText(25),
                        style = MaterialTheme.typography.titleMedium,
                        color = textColor,
                        textDecoration = textDecoration
                    )

                    Text(
                        text = task.description.limitText(40),
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor,
                        textDecoration = textDecoration
                    )
                }

                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { showDialogDetail = true },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Ver detalhes",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Excluir",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Excluir Tarefa") },
            text = {
                Text(
                    text = buildAnnotatedString {
                        append("Tem certeza que deseja excluir esta tarefa: ")

                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(task.title.limitText(25))
                        }

                        append("?")
                    },
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            shape = RoundedCornerShape(10.dp),
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onDelete()
                }) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showDialogDetail) {
        Dialog(
            onDismissRequest = { showDialogDetail = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(end = 48.dp)
                        )

                        IconButton(
                            onClick = { showDialogDetail = false },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fechar",
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}