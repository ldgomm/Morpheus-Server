package me.ldgomm.server.api.routes.userclient

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import me.ldgomm.model.entity.auth.AuthenticationApiResponse
import me.ldgomm.model.repository.userclient.ClientRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.ClientRoute
import me.ldgomm.server.api.endpoints.Endpoint.UnauthorizedRoute
import me.ldgomm.server.util.extension.invalidSession
import me.ldgomm.server.util.session.UserSession

fun Routing.userClientRoute(app: Application, clientRepositoriable: ClientRepositoriable) {
    authenticate("auth-session") {
        route(ClientRoute.path) {
            get {
                val userSession = call.principal<UserSession>()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        call.respond(OK,
                                     AuthenticationApiResponse(success = true,
                                                               client = clientRepositoriable.readClient(
                                                                   userSession.idSession)))
                    } catch (e: Exception) {
                        app.log.info("Invalid saving user: ${e.message}")
                        call.respondRedirect(UnauthorizedRoute.path)
                    }
                }
            }

            delete {
                val userSession: UserSession? = call.principal()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        call.sessions.clear<UserSession>()
                        if (clientRepositoriable.deleteClient(userSession.idSession)) {
                            app.log.info("User successfully deleted")
                            call.respond(OK, AuthenticationApiResponse(success = true))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid saving user: ${e.message}")
                        call.respond(BadRequest, AuthenticationApiResponse(success = false))
                    }
                }
            }
        }
    }
}