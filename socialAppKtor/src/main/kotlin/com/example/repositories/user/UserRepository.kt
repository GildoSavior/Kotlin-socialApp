package com.example.repositories.user

import com.example.models.AuthResponse
import com.example.models.SignInParams
import com.example.models.SignUpParams
import com.example.util.Response

interface UserRepository {
    suspend fun singUp(params: SignUpParams): Response<AuthResponse>
    suspend fun singIn(params: SignInParams): Response<AuthResponse>
}