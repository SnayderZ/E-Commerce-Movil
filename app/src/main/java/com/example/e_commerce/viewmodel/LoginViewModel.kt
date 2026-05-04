package com.example.e_commerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val message: String? = null,
    val isLoginSuccessful: Boolean = false
)

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { currentState ->
            currentState.copy(username = username, message = null)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password, message = null)
        }
    }

    fun login() {
        val username = _uiState.value.username.trim()
        val password = _uiState.value.password

        if (username.isBlank() || password.isBlank()) {
            _uiState.update { currentState ->
                currentState.copy(
                    message = "Ingrese usuario y contraseña",
                    isLoginSuccessful = false
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, message = null, isLoginSuccessful = false)
            }

            val result = authRepository.login(username = username, password = password)

            result.fold(
                onSuccess = { response ->
                    val hasToken = !response.token.isNullOrBlank()
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            message = if (hasToken) "Login correcto" else "Usuario o contraseña incorrectos",
                            isLoginSuccessful = hasToken
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            message = exception.message ?: "Error de conexión",
                            isLoginSuccessful = false
                        )
                    }
                }
            )
        }
    }
}
