package com.example.e_commerce.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("message")
    val message: String? = null
)
