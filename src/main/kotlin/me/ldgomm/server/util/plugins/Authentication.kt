package me.ldgomm.server.util.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import me.ldgomm.server.api.endpoints.Endpoint.UnauthorizedRoute
import me.ldgomm.server.util.session.UserSession

fun Application.configureAuthentication() {
    install(Authentication) {
        session<UserSession>(name = "auth-session") {
            validate { it }
            challenge { call.respondRedirect(UnauthorizedRoute.path) }
        }
    }
}