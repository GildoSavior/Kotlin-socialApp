package com.example.di

import com.example.dao.user.UserDao
import com.example.dao.user.UserDaoImp
import com.example.repositories.user.UserRepository
import com.example.repositories.user.UserRepositoryImpl
import org.koin.dsl.module
val appModule = module {
    single<UserRepository> {UserRepositoryImpl(get())}
    single<UserDao> { UserDaoImp() }

}