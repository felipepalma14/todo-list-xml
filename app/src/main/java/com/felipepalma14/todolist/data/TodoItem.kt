package com.felipepalma14.todolist.data

data class TodoItem(
    val title: String,
    val status: Status,
    val priority: Priority
)

enum class Status {
    CONCLUIDO,
    EM_ANDAMENTO,
    NAO_INICIADO
}

enum class Priority {
    ALTA,
    MEDIA,
    BAIXA
}