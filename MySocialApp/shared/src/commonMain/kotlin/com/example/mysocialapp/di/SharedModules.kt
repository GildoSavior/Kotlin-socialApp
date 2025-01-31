package com.example.mysocialapp.di

import com.example.mysocialapp.auth.data.AuthRepositoryImpl
import com.example.mysocialapp.auth.data.AuthService
import com.example.mysocialapp.auth.domain.repository.AuthRepository
import com.example.mysocialapp.auth.domain.usecase.SignInUseCase
import com.example.mysocialapp.auth.domain.usecase.SignUpUseCase
import com.example.mysocialapp.common.util.providerDispatcher
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> {AuthRepositoryImpl(get(), get())}
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}


private val utilityModule = module {
    factory { providerDispatcher() }
}


fun getSharedModules() = listOf(authModule, utilityModule)