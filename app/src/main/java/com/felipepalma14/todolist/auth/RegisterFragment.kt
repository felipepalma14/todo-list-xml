package com.felipepalma14.todolist.auth

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is AuthState.Navigate.ToLogin -> {
                    findNavController().navigate(R.id.action_register_to_login)
                }
                is AuthState.Error -> {
                    // Handle error state, e.g., show a Toast or Snackbar
                    binding.editTextConfirmPassword.error = uiState.message
                    binding.editTextPassword.error = uiState.message
                }
                else -> Unit
            }
        }
        with(binding) {
            buttonRegister.setOnClickListener {
                val email = editTextEmail.text.toString()
                val password = editTextPassword.text.toString()
                val confirmPassword = editTextPassword.text.toString()
                viewModel.register(email, password,confirmPassword)
            }
        }
    }
}