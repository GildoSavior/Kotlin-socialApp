package com.example.mysocialapp.auth.domain.repository

import com.example.mysocialapp.auth.domain.model.AuthResultData
import  com.example.mysocialapp.common.util.Result

internal interface AuthRepository {
    suspend fun signUp(name: String, email: String, password:String) : Result<AuthResultData>
    suspend fun signIn(email: String, password: String): Result<AuthResultData>
}