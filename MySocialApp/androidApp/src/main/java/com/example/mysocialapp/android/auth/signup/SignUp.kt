package com.example.mysocialapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.example.mysocialapp.android.destinations.HomeScreenDestination

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun SignUp(
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = koinViewModel()

    SignUpScreen(
        uiState = viewModel.uiState,
        onUserNameChange = viewModel::updateUserName,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToHome = {
               navigator.navigate(HomeScreenDestination)
        },
        onSignUpClick = viewModel::signUp
    )
}