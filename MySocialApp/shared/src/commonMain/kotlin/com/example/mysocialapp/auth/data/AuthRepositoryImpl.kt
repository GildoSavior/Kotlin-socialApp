package com.example.mysocialapp.auth.data

import com.example.mysocialapp.auth.domain.model.AuthResultData
import com.example.mysocialapp.auth.domain.repository.AuthRepository
import com.example.mysocialapp.common.util.DispatcherProvider
import com.example.mysocialapp.common.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val authService:AuthService
) : AuthRepository {

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {
      return withContext(dispatcher.io) {
          try {
              val request = SignUpRequest(name, email, password)

              val authResponse = authService.signUp(request)

              if(authResponse.data == null) {
                  Result.Error(
                      message = authResponse.errorMessage!!
                  )
              } else {
                  Result.Sucess(
                      data = authResponse.data.toAuthResultData()
                  )
              }
          } catch (e: Exception) {
              Result.Error(
                  message = e.message ?: "Oops, we could not send your request, try later"
              )
          }
      }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val request = SignInRequest(email, password)

                val authResponse = authService.signIn(request)

                if(authResponse.data == null) {
                    Result.Error(
                        message = authResponse.errorMessage!!
                    )
                } else {
                    Result.Sucess(
                        data = authResponse.data.toAuthResultData()
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = e.message ?: "Oops, we could not send your request, try later"
                )
            }
        }
    }
}