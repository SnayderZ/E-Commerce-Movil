package com.example.e_commerce.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.repository.AuthRepositoryImpl
import com.example.e_commerce.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase = LoginUseCase(AuthRepositoryImpl())
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email, message = null)
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(password = password, message = null)
        }
    }

    fun login() {
        val email = _uiState.value.email.trim()
        val password = _uiState.value.password

        if (email.isBlank() || password.isBlank()) {
            _uiState.update { currentState ->
                currentState.copy(
                    message = "Ingrese email y contraseña",
                    isLoginSuccessful = false
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    message = null,
                    isLoginSuccessful = false
                )
            }

            val result = loginUseCase(
                email = email,
                password = password
            )

            result.fold(
                onSuccess = { user ->
                    val hasToken = !user.accessToken.isNullOrBlank()

                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            message = if (hasToken) {
                                "Login correcto"
                            } else {
                                "Respuesta inválida del servidor"
                            },
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
