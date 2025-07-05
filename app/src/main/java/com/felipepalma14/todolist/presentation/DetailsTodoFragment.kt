package com.felipepalma14.todolist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.data.Priority
import com.felipepalma14.todolist.data.Status
import com.felipepalma14.todolist.data.TodoItem
import com.felipepalma14.todolist.databinding.FragmentDetailsTodoBinding
import com.felipepalma14.todolist.databinding.FragmentTodoListBinding

class DetailsTodoFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel

    private var _binding: FragmentDetailsTodoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)
        viewModel.editItem?.let { todoItem ->
            binding.editTextTitle.setText(todoItem.title)
            binding.spinnerStatus.setSelection(getStatusFromSpinner(todoItem))
            binding.spinnerPriority.setSelection(getPriorityFromSpinner(todoItem))

            binding.buttonSave.setOnClickListener {
                val title = binding.editTextTitle.text.toString()

                if (title.isBlank()) {
                    Toast.makeText(requireContext(), "Título obrigatório", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.addTodo(TodoItem(0, title, getStatusFromSpinner(), getPriorityFromSpinner()))
                Toast.makeText(requireContext(), "Item atualizado!", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_details_todo_to_todo_list)

            }
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

    private fun getStatusFromSpinner(todoItem: TodoItem): Int {
        return when (todoItem.status) {
            Status.NAO_INICIADO -> 0
            Status.EM_ANDAMENTO -> 1
            Status.CONCLUIDO -> 2
        }
    }

    private fun getPriorityFromSpinner(todoItem: TodoItem): Int {
        return when (todoItem.priority) {
            Priority.ALTA -> 0
            Priority.MEDIA -> 1
            Priority.BAIXA -> 2
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}