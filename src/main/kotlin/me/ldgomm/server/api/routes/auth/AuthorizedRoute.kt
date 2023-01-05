package me.ldgomm.server.api.routes.auth

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.ldgomm.model.entity.auth.AuthenticationApiResponse
import me.ldgomm.server.api.endpoints.Endpoint.AuthorizedRoute

fun Routing.authorizedRoute() {
    authenticate("auth-session") {
        route(AuthorizedRoute.path) {
            get {
                call.respond(OK, AuthenticationApiResponse(success = true, message = "User authenticated"))
            }
        }
    }
}