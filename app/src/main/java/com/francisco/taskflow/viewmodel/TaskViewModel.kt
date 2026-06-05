package com.francisco.taskflow.viewmodel

import androidx.lifecycle.*
import com.francisco.taskflow.data.*
import com.francisco.taskflow.repository.*
import kotlinx.coroutines.*

class TaskViewModel(
    private val repository: TaskRepository,
    @Suppress("unused")
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val tasks = repository.tasks

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.insert(
                Task(
                    title = title,
                    description = description
                )
            )
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            repository.update(
                task.copy(
                    isDone = !task.isDone
                )
            )
        }
    }
}