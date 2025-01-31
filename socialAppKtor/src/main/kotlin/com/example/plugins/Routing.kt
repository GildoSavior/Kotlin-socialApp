package com.example.plugins

import com.example.route.authRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
      authRoute()
    }
}
