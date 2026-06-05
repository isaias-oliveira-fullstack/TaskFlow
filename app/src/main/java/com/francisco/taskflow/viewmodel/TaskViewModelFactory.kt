package com.francisco.taskflow.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.*
import com.francisco.taskflow.repository.*

class TaskViewModelFactory(
    private val repository: TaskRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        val handle: SavedStateHandle = extras.createSavedStateHandle()

        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository, handle) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class: ${modelClass.name}"
        )
    }
}