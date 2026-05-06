package com.example.e_commerce.data.remote.dto.Auth

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("accessToken")
    val accessToken: String? = null
)
