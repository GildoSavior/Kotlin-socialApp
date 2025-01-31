package com.example.route

import com.example.models.AuthResponse//import io.ktor.request.*
import com.example.models.SignInParams
//import io.ktor.response.*
import com.example.models.SignUpParams
import com.example.repositories.user.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.authRoute() {
    val repository by inject<UserRepository>()

    route("/signup") {
        post {
            val params: SignUpParams? = call.receiveNullable<SignUpParams>()

            if (params == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage =  "Invalid Credentials"
                    )
                )
                return@post
            }

            val result = repository.singUp(params = params)
            call.respond(
                status  = result.code,
                message =  result.data
            )
        }
    }


    route("/login") {
        post {
            val params: SignInParams? = call.receiveNullable<SignInParams>()

            if (params == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = AuthResponse(
                        errorMessage =  "Invalid Credentials"
                    )
                )
                return@post
            }

            val result = repository.singIn(  params = params)
            call.respond(
                status  = result.code,
                message =  result.data
            )
        }
    }
}
