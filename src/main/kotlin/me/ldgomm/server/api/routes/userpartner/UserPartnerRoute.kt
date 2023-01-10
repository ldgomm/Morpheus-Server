package me.ldgomm.server.api.routes.userpartner

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import me.ldgomm.model.entity.auth.AuthenticationApiResponse
import me.ldgomm.model.repository.userpartner.PartnerRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.PartnerRoute
import me.ldgomm.server.api.endpoints.Endpoint.UnauthorizedRoute
import me.ldgomm.server.util.extension.invalidSession
import me.ldgomm.server.util.session.UserSession

fun Routing.userPartnerRoute(app: Application, partnerRepositoriable: PartnerRepositoriable) {
    authenticate("auth-session") {
        route(PartnerRoute.path) {
            get {
                val userSession: UserSession? = call.principal()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        call.respond(OK,
                                     AuthenticationApiResponse(success = true,
                                                               partner = partnerRepositoriable.readPartner(
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
                        if (partnerRepositoriable.deletePartner(userSession.idSession)) {
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
