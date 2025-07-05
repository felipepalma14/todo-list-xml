package com.felipepalma14.todolist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.data.Priority
import com.felipepalma14.todolist.data.Status
import com.felipepalma14.todolist.data.TodoItem
import com.felipepalma14.todolist.databinding.FragmentAddTodoBinding

class AddTodoFragment : Fragment() {
    private lateinit var viewModel: TodoViewModel
    private var _binding: FragmentAddTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)

        _binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val status = binding.spinnerStatus.selectedItem.toString()
            val priority = binding.spinnerPriority.selectedItem.toString()

            if (title.isBlank()) {
                Toast.makeText(requireContext(), "Título obrigatório", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            viewModel.addTodo(TodoItem(0, title, getStatusFromSpinner(), getPriorityFromSpinner()))
            Toast.makeText(requireContext(), "Item adicionado!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_add_todo_to_todo_list)

        }
    }

    private fun getStatusFromSpinner(): Status {
        return when (binding.spinnerStatus.selectedItem.toString()) {
            "Concluído" -> Status.CONCLUIDO
            "Em andamento" -> Status.EM_ANDAMENTO
            "Não iniciado" -> Status.NAO_INICIADO
            else -> Status.NAO_INICIADO // Default case
        }
    }

    private fun getPriorityFromSpinner(): Priority {
        return when (binding.spinnerPriority.selectedItem.toString()) {
            "Alta" -> Priority.ALTA
            "Média" -> Priority.MEDIA
            "Baixa" -> Priority.BAIXA
            else -> Priority.MEDIA // Default case
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}