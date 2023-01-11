package com.github.flatit.ui.todos

import androidx.lifecycle.ViewModel
import com.github.flatit.data.TodosRepository

class TodosViewModel(
    private val todosRepository: TodosRepository
) : ViewModel() {

    val items = todosRepository.getItems()
}