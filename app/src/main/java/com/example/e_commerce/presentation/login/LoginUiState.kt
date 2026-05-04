package com.example.e_commerce.presentation.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val message: String? = null,
    val isLoginSuccessful: Boolean = false
)
