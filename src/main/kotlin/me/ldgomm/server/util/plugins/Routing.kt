package me.ldgomm.server.util.plugins

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.ldgomm.model.entity.offer.*
import me.ldgomm.server.api.routes.auth.authorizedRoute
import me.ldgomm.server.api.routes.auth.googleTokenVerificationRoute
import me.ldgomm.server.api.routes.auth.unauthorizedRoute
import me.ldgomm.server.api.routes.userclient.userClientRoute
import me.ldgomm.model.repository.auth.AuthenticationRepositoriable
import me.ldgomm.model.repository.offer.OfferRepositoriable
import me.ldgomm.model.repository.userclient.ClientRepositoriable
import me.ldgomm.model.repository.userpartner.PartnerRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.RootRoute
import me.ldgomm.server.api.routes.auth.appleTokenVerificationRoute
import me.ldgomm.server.api.routes.offer.offerRoute
import me.ldgomm.server.api.routes.userpartner.userPartnerRoute
import me.ldgomm.server.util.constant.Constants.offer
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        val authenticationRepositoriable: AuthenticationRepositoriable by inject()
        val clientRepositoriable: ClientRepositoriable by inject()
        val partnerRepositoriable: PartnerRepositoriable by inject()
        val offerRepositoriable: OfferRepositoriable by inject()

        authorizedRoute()
        appleTokenVerificationRoute(application, authenticationRepositoriable)
        googleTokenVerificationRoute(application, authenticationRepositoriable)
        userClientRoute(application, clientRepositoriable)
        userPartnerRoute(application, partnerRepositoriable)
        offerRoute(application, offerRepositoriable)
        unauthorizedRoute()

        route(RootRoute.path) {
            get {
                call.respond( OK, OfferApiResponse(success = true, offer = offer))
            }
        }
    }
}
