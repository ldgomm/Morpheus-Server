package me.ldgomm.server.util.extension

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import me.ldgomm.server.api.endpoints.Endpoint

suspend fun PipelineContext<Unit, ApplicationCall>.invalidSession(app: Application) {
    app.log.info("Invalid session")
    call.respondRedirect(Endpoint.UnauthorizedRoute.path)
}

suspend fun PipelineContext<Unit, ApplicationCall>.unauthorizedRoute(app: Application) {
    app.log.info("Error creating new user")
    call.respondRedirect(Endpoint.UnauthorizedRoute.path)
}