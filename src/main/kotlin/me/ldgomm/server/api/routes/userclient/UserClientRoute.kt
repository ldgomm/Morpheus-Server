package me.ldgomm.server.api.routes.userclient

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Conflict
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import me.ldgomm.model.entity.auth.AuthenticationApiResponse
import me.ldgomm.model.entity.user.Client
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
                        val client: Client? = clientRepositoriable.readClient(userSession.idSession)
                        if (client != null) {
                            call.respond(OK,
                                         AuthenticationApiResponse(success = true,
                                                                   message = "Client exists",
                                                                   client = client))
                        } else {
                            call.respond(NotFound,
                                         AuthenticationApiResponse(success = false, message = "Client not found"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid getting client: ${e.message}")
                        call.respond(BadRequest,
                                     AuthenticationApiResponse(success = false,
                                                               message = "Error getting client: ${e.message}"))
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
                            app.log.info("Client successfully deleted")
                            call.respond(OK, AuthenticationApiResponse(success = true, "Client deleted"))
                        } else {
                            call.respond(Conflict,
                                         AuthenticationApiResponse(success = false, message = "User was not deleted"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid deleting user: ${e.message}")
                        call.respond(BadRequest,
                                     AuthenticationApiResponse(success = false, message = "Client was not deleted"))
                    }
                }
            }
        }
    }
}