package me.ldgomm.server.api.routes.auth

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.ldgomm.model.entity.auth.AuthenticationApiResponse
import me.ldgomm.model.repository.userclient.ClientRepositoriable
import me.ldgomm.model.repository.userpartner.PartnerRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.AuthorizedRoute
import me.ldgomm.server.util.extension.invalidSession
import me.ldgomm.server.util.session.UserSession

fun Routing.authorizedRoute(app: Application,
                            clientRepositoriable: ClientRepositoriable,
                            partnerRepositoriable: PartnerRepositoriable) {
    authenticate("auth-session") {
        route(AuthorizedRoute.path) {
            get {
                val userSession: UserSession? = call.principal()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    call.respond(OK,
                                 AuthenticationApiResponse(success = true,
                                                           message = "User authenticated",
                                                           client = clientRepositoriable.readClient(idClient = userSession.idSession),
                                                           partner = partnerRepositoriable.readPartner(idPartner = userSession.idSession)))
                }
            }
        }
    }
}