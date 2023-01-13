package me.ldgomm.server.api.routes.partner

import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import me.ldgomm.model.entity.auth.AuthenticationApiResponse
import me.ldgomm.model.entity.user.Partner
import me.ldgomm.model.repository.userpartner.PartnerRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.PartnerRoute
import me.ldgomm.server.util.extension.invalidSession
import me.ldgomm.server.util.session.UserSession

fun Routing.partnerRoute(app: Application, partnerRepositoriable: PartnerRepositoriable) {
    authenticate("auth-session") {
        route(PartnerRoute.path) {
            get {
                val userSession = call.principal<UserSession>()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        val partner: Partner? = partnerRepositoriable.readPartner(userSession.idSession)
                        if (partner != null) {
                            call.respond(OK,
                                         AuthenticationApiResponse(success = true,
                                                                   message = "Partner exists",
                                                                   partner = partner))
                        } else {
                            call.respond(NotFound,
                                         AuthenticationApiResponse(success = false, message = "Partner not found"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid getting partner: ${e.message}")
                        call.respond(BadRequest,
                                     AuthenticationApiResponse(success = false,
                                                               message = "Error getting partner: ${e.message}"))
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
                            app.log.info("Partner successfully deleted")
                            call.respond(OK, AuthenticationApiResponse(success = true, "Partner deleted"))
                        } else {
                            call.respond(HttpStatusCode.Conflict,
                                         AuthenticationApiResponse(success = false,
                                                                   message = "Partner was not deleted"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid deleting partner: ${e.message}")
                        call.respond(BadRequest,
                                     AuthenticationApiResponse(success = false, message = "Partner was not deleted"))
                    }
                }
            }
        }
    }
}
