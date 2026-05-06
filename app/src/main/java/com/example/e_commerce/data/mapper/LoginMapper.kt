package com.example.e_commerce.data.mapper

import com.example.e_commerce.data.remote.dto.Auth.LoginResponseDto
import com.example.e_commerce.domain.model.AuthUser

fun LoginResponseDto.toDomain(): AuthUser {
    return AuthUser(
        accessToken = accessToken
    )
}
