package com.example.mysocialapp.android.auth.login

import androidx.compose.runtime.Composable
import com.example.mysocialapp.android.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable

fun Login(
    navigator: DestinationsNavigator
) {
    val viewModel : LoginViewModel = koinViewModel()

    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSingInClick = viewModel::singIn,
        onNavigateToHome = {
            navigator.navigate(HomeScreenDestination)


//                direction = HomeScreenDestination,  // Use a direction gerada pelo compose-destinations
//                onlyIfResumed = true,
//                navOptions = null
//            )
        }
    )
}