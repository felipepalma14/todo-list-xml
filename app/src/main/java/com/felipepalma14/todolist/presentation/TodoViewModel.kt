package com.felipepalma14.todolist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipepalma14.todolist.data.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel: ViewModel() {
    val todos = MutableLiveData<MutableList<TodoItem>>()

    var editItem: TodoItem? = null

    fun addTodo(todo: TodoItem) {
        CoroutineScope(Dispatchers.Main).launch {
            todos.value?.let {
                it.add(todo.copy(id = it.size))
                todos.value = it
            } ?: run {
                todos.value = mutableListOf(todo)
            }
        }
    }

    fun saveToEditTodo(todo: TodoItem){
        CoroutineScope(Dispatchers.Main).launch {
            editItem = todo
        }
    }

    fun updateTodo(todo: TodoItem){
        CoroutineScope(Dispatchers.Main).launch {
            todos.value?.let {
                it[todo.id] = todo
            }
            editItem = null
        }
    }
}