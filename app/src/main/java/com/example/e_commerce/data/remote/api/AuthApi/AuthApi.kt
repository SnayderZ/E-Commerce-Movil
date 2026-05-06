package com.example.e_commerce.data.remote.api.AuthApi

import com.example.e_commerce.data.remote.dto.Auth.LoginRequestDto
import com.example.e_commerce.data.remote.dto.Auth.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("user/Login")
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>
}