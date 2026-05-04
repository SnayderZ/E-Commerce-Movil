package com.example.e_commerce.domain.usecase

import com.example.e_commerce.domain.model.AuthUser
import com.example.e_commerce.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<AuthUser> {
        return authRepository.login(
            email = email,
            password = password
        )
    }
}
