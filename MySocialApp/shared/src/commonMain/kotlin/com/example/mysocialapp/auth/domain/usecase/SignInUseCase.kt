package com.example.mysocialapp.auth.domain.usecase


import com.example.mysocialapp.auth.domain.model.AuthResultData
import com.example.mysocialapp.common.util.Result
import com.example.mysocialapp.auth.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInUseCase: KoinComponent {
    private  val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        password : String
    ): Result<AuthResultData> {

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

        return repository.signIn(email, password)
    }
}