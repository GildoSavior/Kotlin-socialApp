package com.example.mysocialapp.android.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialapp.auth.domain.usecase.SignUpUseCase
import com.example.mysocialapp.common.util.Result
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {
    var uiState by mutableStateOf(SignUpUIState())

    fun  signUp() {
         viewModelScope.launch {
             uiState = uiState.copy(isAuthenticating = true )

             val authResultData = signUpUseCase(uiState.username, uiState.email, uiState.password)

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
                         authenticationSucess = true,
                     )
                 }
             }
         }
    }

    fun updateUserName(userName : String){
        uiState = uiState.copy(username = userName)
    }
    fun updateEmail(email : String){
        uiState = uiState.copy(email = email)
    }
    fun updatePassword(password: String){
        uiState = uiState.copy(password = password)
    }

}

data class SignUpUIState(
    var username: String = "",
    var email:String = "",
    var password:String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucess: Boolean = false,
)