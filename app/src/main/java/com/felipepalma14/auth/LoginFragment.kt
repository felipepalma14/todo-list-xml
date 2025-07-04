package com.felipepalma14.auth

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.databinding.FragmentLoginBinding
import com.felipepalma14.todolist.databinding.FragmentTodoListBinding

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

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
        with(binding) {
            buttonConfirm.setOnClickListener {
//                val email = editTextEmail.text.toString()
//                val password = editTextPassword.text.toString()
//                if (email.isNotEmpty() && password.isNotEmpty()) {
//                    //viewModel.login(email, password)
//                } else {
//                    // Show error message
//                    editTextEmail.error = "Email cannot be empty"
//                    editTextPassword.error = "Password cannot be empty"
//                }

                findNavController().navigate(R.id.action_login_to_todo_list)
            }

            buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }
}