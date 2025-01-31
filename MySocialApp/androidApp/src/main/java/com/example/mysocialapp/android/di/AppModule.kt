package com.example.mysocialapp.android.di

import com.example.mysocialapp.android.auth.login.LoginViewModel
import com.example.mysocialapp.android.auth.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
}