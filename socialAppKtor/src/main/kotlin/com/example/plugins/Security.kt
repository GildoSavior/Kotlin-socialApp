package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.models.AuthResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

//private val jwtAudience = System.getenv("jwt.audience")
//private val jwtIssuer = System.getenv("jwt.domain")
//private val jwtSecret = System.getenv("jwt.secret")

private const val jwtAudience = "jwt-audience"
private const val jwtIssuer = "https://jwt-provider-domain/"
private const val jwtSecret = "secret"
private const val CLAIN = "email"

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
//    val jwtRealm = "ktor sample app"

    authentication {
        jwt {
//            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtIssuer)
                    .build()
            )
            validate { credential ->
                if(credential.payload.getClaim(CLAIN).asString() != null) {
                    JWTPrincipal(payload =  credential.payload)
                } else {
                    null
                }
            }

            challenge {_, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = AuthResponse(
                        errorMessage = "Token is not valid or has expired"
                    )
                )
            }
        }
    }
}


fun generateToken(email: String): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim(CLAIN,email)
//            .withExpiresAt()
            .sign(Algorithm.HMAC256(jwtSecret))
}



