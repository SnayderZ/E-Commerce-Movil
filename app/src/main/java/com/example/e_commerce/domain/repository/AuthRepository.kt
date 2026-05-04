package com.example.e_commerce.domain.repository

import com.example.e_commerce.domain.model.AuthUser

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<AuthUser>
}
