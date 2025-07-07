package com.felipepalma14.todolist.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipepalma14.todolist.auth.model.User
import kotlinx.coroutines.launch

sealed interface AuthState {
    object Idle : AuthState
    sealed interface Navigate : AuthState {
        data object ToTodoList : Navigate
        data object ToLogin : Navigate
        data object ToRegister : Navigate
    }
    data class Error(val message: String) : AuthState
}
class AuthViewModel : ViewModel() {

    private val _uiState = MutableLiveData<AuthState>(AuthState.Idle)
    val uiState: LiveData<AuthState> = _uiState

    private val users = mutableListOf<User>()


    fun login(email: String, password: String) = viewModelScope.launch {
        when {
            (email == "felipe" && password == "123456") -> {
                _uiState.value = AuthState.Navigate.ToTodoList
            }
            users.any { it.email == email && it.password == password } -> {
                _uiState.value = AuthState.Navigate.ToTodoList
            }
            else -> {
                _uiState.value = AuthState.Error("Putz... erro no login ou senha")
            }
        }
        _uiState.value = AuthState.Idle // Reset state when ViewModel is cleared
    }
    fun register(email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        if (confirmPassword != password || email.isBlank() || password.isBlank()) {
            _uiState.value = AuthState.Error("As senhas não conferem")
            return@launch
        }
        users.add(User(email, password))

        _uiState.value = AuthState.Navigate.ToLogin
        _uiState.value = AuthState.Idle // Reset state when ViewModel is cleared
    }

    fun navigateToRegister() {
        _uiState.value = AuthState.Navigate.ToRegister
    }

    override fun onCleared() {
        super.onCleared()
        _uiState.value = AuthState.Idle // Reset state when ViewModel is cleared
    }
}
