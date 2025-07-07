package com.felipepalma14.todolist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.adapter.TodoAdapter
import com.felipepalma14.todolist.data.TodoItem
import com.felipepalma14.todolist.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel

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

        viewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
        viewModel.todos.observe(viewLifecycleOwner) { data ->
            // Update UI with new data
            recyclerView.adapter = TodoAdapter(data, ::onClick)
        }
    }

    fun onClick(item: TodoItem){
        viewModel.saveToEditTodo(item)
        findNavController().navigate(R.id.action_todo_list_to_details_todo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}