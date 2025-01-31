package com.example.mysocialapp.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialapp.auth.domain.usecase.SignInUseCase
import com.example.mysocialapp.common.util.Result
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInUseCase: SignInUseCase
): ViewModel() {
    var uiState by mutableStateOf(LoginUIState())

    fun singIn(){
        viewModelScope.launch {
            uiState = uiState.copy(isAuthenticating = true)

            val authResultData = signInUseCase(uiState.email, uiState.password)

            uiState = when (authResultData) {
                is Result.Error -> {
                    uiState.copy(
                        isAuthenticating = false,
                        authErrorMessage = authResultData.message
                    )
                }
                is Result.Sucess -> {
                    uiState.copy(
                        isAuthenticating = false,
                        authenticationSuccess  = true,
                    )
                }
            }
        }
    }

    fun updateEmail(email : String){
        uiState =  uiState.copy(email = email)
    }
    fun updatePassword(password: String){
        uiState = uiState.copy(password = password)
    }
}

data class LoginUIState(
    var email: String = "",
    var password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = "",
    var authenticationSuccess: Boolean = false,
)
