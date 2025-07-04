package com.felipepalma14.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.data.Priority
import com.felipepalma14.todolist.data.Status
import com.felipepalma14.todolist.data.TodoItem

class TodoAdapter(private val items: List<TodoItem>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewTitle)
        val status: TextView = itemView.findViewById(R.id.textViewStatus)
        val priority: TextView = itemView.findViewById(R.id.textViewPriority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.status.text = when (item.status) {
            Status.CONCLUIDO -> "Concluído"
            Status.EM_ANDAMENTO -> "Em andamento"
            Status.NAO_INICIADO -> "Não iniciado"
        }
        holder.priority.text = when (item.priority) {
            Priority.ALTA -> "Alta"
            Priority.MEDIA -> "Média"
            Priority.BAIXA -> "Baixa"
        }
    }

    override fun getItemCount(): Int = items.size
}