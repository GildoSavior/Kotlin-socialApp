package com.example.mysocialapp.android

import android.app.Application
import com.example.mysocialapp.android.di.appModule
import com.example.mysocialapp.di.getSharedModules
import org.koin.core.context.startKoin

class SocialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}