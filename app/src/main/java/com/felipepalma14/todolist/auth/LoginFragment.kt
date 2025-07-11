package com.felipepalma14.todolist.auth

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is AuthState.Navigate.ToTodoList -> {
                    findNavController().navigate(R.id.action_login_to_todo_list)
                }
                is AuthState.Navigate.ToRegister -> {
                    findNavController().navigate(R.id.action_login_to_register)
                }
                is AuthState.Error -> {
                    // Handle error state, e.g., show a Toast or Snackbar
                    binding.editTextEmail.error = uiState.message
                    binding.editTextPassword.error = uiState.message
                }
                else -> Unit
            }
        }
        with(binding) {
            buttonConfirm.setOnClickListener {
                val email = editTextEmail.text.toString()
                val password = editTextPassword.text.toString()
                viewModel.login(email, password)
            }

            buttonRegister.setOnClickListener {
                viewModel.navigateToRegister()
            }
        }
    }
}