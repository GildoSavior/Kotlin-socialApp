package com.example.mysocialapp.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class AndroidDispatcher : DispatcherProvider{
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}

internal actual fun providerDispatcher(): DispatcherProvider  = AndroidDispatcher()