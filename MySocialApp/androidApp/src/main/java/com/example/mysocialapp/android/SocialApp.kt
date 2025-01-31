package com.example.mysocialapp.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.mysocialapp.android.auth.login.LoginScreen
import com.example.mysocialapp.android.auth.login.LoginUIState
import com.example.mysocialapp.android.auth.login.LoginViewModel
import com.example.mysocialapp.android.auth.signup.SignUpScreen
import com.example.mysocialapp.android.auth.signup.SignUpUIState
import com.example.mysocialapp.android.auth.signup.SignUpViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.utils.composable
import org.koin.androidx.compose.koinViewModel


@Composable
@Destination
fun SocialApp() {
    val navController = rememberNavController()
    DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)

    //Validar se esse codigo é apagado ou n
//    NavHost(
//        navController = navController,
//        startDestination = "signup"
//    ) {
//        composable("signup") {
//            val viewModel: SignUpViewModel = koinViewModel()
//            SignUpScreen(
//                uiState = viewModel.uiState,
//                onUserNameChange = viewModel::updateUserName,
//                onEmailChange = viewModel::updateEmail,
//                onPasswordChange = viewModel::updatePassword,
//            )
//        }
//        composable("login") {
//            val viewModel: LoginViewModel = koinViewModel()
//            LoginScreen(
//                uiState = viewModel.uiState,
//                onEmailChange = viewModel::updateEmail,
//                onPasswordChange = viewModel::updatePassword
//            )
//        }
//    }


}


