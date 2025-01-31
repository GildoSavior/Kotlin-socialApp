package com.example.mysocialapp.android.auth.signup


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysocialapp.android.R
import com.example.mysocialapp.android.common.components.CustomTextField
import com.example.mysocialapp.android.common.theming.ButtonHeight
import com.example.mysocialapp.android.common.theming.DarkColors
import com.example.mysocialapp.android.common.theming.ExtraLargeSpacing
import com.example.mysocialapp.android.common.theming.LargeSpacing
import com.example.mysocialapp.android.common.theming.LightColors
import com.example.mysocialapp.android.common.theming.MediumSpacing
import com.example.mysocialapp.android.common.theming.SocialAppTheme


@SuppressLint("ShowToast")
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpUIState,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = if (isSystemInDarkTheme()) DarkColors.background else LightColors.surface
                )
                .padding(
                    top = ExtraLargeSpacing + LargeSpacing,
                    start = LargeSpacing + MediumSpacing,
                    end = LargeSpacing + MediumSpacing,
                    bottom = LargeSpacing
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            CustomTextField(value = uiState.username, onValueChange = onUserNameChange, hint = R.string.username_hint )

            CustomTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                hint = R.string.email_hint,
                keyboardType = KeyboardType.Email
            )

            CustomTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                hint = R.string.password_hint,
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
            )

            Button(
                onClick = {
                   onSignUpClick()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(ButtonHeight),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = stringResource(id = R.string.signup_button_hint))
            }
        }

        if(uiState.isAuthenticating) {
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(
        key1 = uiState.authenticationSucess,
        key2 = uiState.authErrorMessage,

        block = {
            if(uiState.isAuthenticating) {
                onNavigateToHome()
            }

            if(uiState.authErrorMessage != null) {
                 Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_SHORT)
            }
        }
    )
}





@Preview
@Composable
fun SignUpScreenPreview() {
    SocialAppTheme {
        SignUpScreen(
            uiState = SignUpUIState(),
            onUserNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToHome = {},
            onSignUpClick = {}
        )
    }
}


