package com.example.e_commerce.data.remote.dto.Auth

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
