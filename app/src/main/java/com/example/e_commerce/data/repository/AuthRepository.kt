package com.example.e_commerce.data.repository

import com.example.e_commerce.data.api.AuthApi
import com.example.e_commerce.data.api.ApiClient
import com.example.e_commerce.data.model.LoginRequest
import com.example.e_commerce.data.model.LoginResponse
import java.io.IOException

class AuthRepository(
    private val authApi: AuthApi = ApiClient.authApi
) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = authApi.login(LoginRequest(username = username, password = password))
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Result.success(body)
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
