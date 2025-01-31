package com.example.dao.user

import com.example.models.SignUpParams
import com.example.models.User

interface UserDao {
    suspend fun insert(params: SignUpParams) : User?
    suspend fun findByEmail(email: String ): User?
}