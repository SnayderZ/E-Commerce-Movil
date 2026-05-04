package com.example.e_commerce.data.remote.api

import com.example.e_commerce.data.remote.dto.LoginRequestDto
import com.example.e_commerce.data.remote.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("user/Login")
    suspend fun login(@Body request: LoginRequestDto): Response<LoginResponseDto>
}
