package com.example

import com.example.dao.DataBaseFactory
import com.example.di.configureDI
import com.example.plugins.configureRouting
import com.example.plugins.configureSecurity
import com.example.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DataBaseFactory.init()
    configureSerialization()
    configureDI()
    configureSecurity()
    configureRouting()
}
