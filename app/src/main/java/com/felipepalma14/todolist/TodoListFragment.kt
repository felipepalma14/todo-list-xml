package com.felipepalma14.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepalma14.todolist.adapter.TodoAdapter
import com.felipepalma14.todolist.data.Priority
import com.felipepalma14.todolist.data.Status
import com.felipepalma14.todolist.data.TodoItem
import com.felipepalma14.todolist.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerViewTodos
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Exemplo de dados
        val items = listOf(
            TodoItem("Comprar pão", Status.CONCLUIDO, Priority.BAIXA),
            TodoItem("Estudar Kotlin", Status.EM_ANDAMENTO, Priority.ALTA),
            TodoItem("Fazer exercícios", Status.NAO_INICIADO, Priority.MEDIA)
        )

        recyclerView.adapter = TodoAdapter(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}