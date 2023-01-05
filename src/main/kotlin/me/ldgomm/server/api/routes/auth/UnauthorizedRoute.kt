package me.ldgomm.server.api.routes.auth

import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.ldgomm.server.api.endpoints.Endpoint.UnauthorizedRoute

fun Routing.unauthorizedRoute() {
    route(UnauthorizedRoute.path) {
        get { call.respond(Unauthorized, "You are not authorized") }
    }
}