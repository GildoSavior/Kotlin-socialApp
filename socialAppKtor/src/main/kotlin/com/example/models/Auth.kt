package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class SignInParams (
    val email: String,
    val password: String
)

@Serializable
data class SignUpParams(
    val name: String,
    val email: String,
    val password: String
)


data class AuthResponse(
    val data: AuthResponseData? = null,
    val errorMessage: String? = null
)

@Serializable
data class AuthResponseData(
    val id: Int,
    val name: String,
    val bio: String,
    val token: String,
    val avatar: String?,
    val followerCount: Int? = 0,
    val followingCount: Int? = 0
)