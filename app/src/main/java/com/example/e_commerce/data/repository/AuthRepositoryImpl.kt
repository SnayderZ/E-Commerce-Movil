package com.example.e_commerce.data.repository

import com.example.e_commerce.data.mapper.toDomain
import com.example.e_commerce.data.remote.api.ApiClient
import com.example.e_commerce.data.remote.api.AuthApi
import com.example.e_commerce.data.remote.dto.LoginRequestDto
import com.example.e_commerce.domain.model.AuthUser
import com.example.e_commerce.domain.repository.AuthRepository
import java.io.IOException

class AuthRepositoryImpl(
    private val authApi: AuthApi = ApiClient.authApi) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<AuthUser> {
        return try {
            val response = authApi.login(
                LoginRequestDto(
                    email = email,
                    password = password
                )
            )

            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.success(body.toDomain())
            } else {
                Result.failure(Exception("Usuario o contraseña incorrectos"))
            }
        } catch (exception: IOException) {
            Result.failure(Exception("Error de conexión: ${exception.message}"))
        } catch (exception: Exception) {
            Result.failure(Exception("Error: ${exception.message}"))
        }
    }
}
