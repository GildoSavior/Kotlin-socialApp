package com.example.mysocialapp.auth.domain.usecase

import com.example.mysocialapp.auth.domain.model.AuthResultData
import com.example.mysocialapp.common.util.Result
import com.example.mysocialapp.auth.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase: KoinComponent {
    private  val repository: AuthRepository by inject()

    suspend operator fun invoke(
        name: String,
        email: String,
        password : String
    ): Result<AuthResultData> {

        if(name.isBlank() || name.length < 3) {
            return  Result.Error(
                message = "Invalid name"
            )
        }

        if(email.isBlank() || "@" !in email) {
            return  Result.Error(
                message = "Invalid email"
            )
        }

        if(password.isBlank() || password.length < 6) {
            return  Result.Error(
                message = "Invalid  password"
            )
        }

        return repository.signUp(name, email, password)
    }
}