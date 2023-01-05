package me.ldgomm.server.api.routes.offer

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import me.ldgomm.model.entity.offer.Offer
import me.ldgomm.model.entity.offer.OfferApiRequest
import me.ldgomm.model.entity.offer.OfferApiResponse
import me.ldgomm.model.repository.offer.OfferRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.OfferRoute
import me.ldgomm.server.api.endpoints.Endpoint.UnauthorizedRoute
import me.ldgomm.server.util.session.UserSession

fun Routing.offerRoute(app: Application, offerRepositoriable: OfferRepositoriable) {
    authenticate("auth-session") {
        route(OfferRoute.path) {
            get {
                val userSession: UserSession? = call.principal()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        val offers: List<Offer> = offerRepositoriable.readOffers()
                        if (offers.isNotEmpty()) {
                            call.respond(OK, OfferApiResponse(success = true, offers = offers))
                        } else {
                            call.respond(OK, OfferApiResponse(success = false, offers = listOf()))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid request: ${e.message}")
                        call.respondRedirect(UnauthorizedRoute.path)
                    }
                }
            }

            post {
                val userSession: UserSession? = call.principal()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        val request: OfferApiRequest = call.receive()
                        if (offerRepositoriable.createOffer(request.offer)) {
                            call.respond(Created, OfferApiResponse(success = true, offer = request.offer))
                        } else {
                            call.respond(BadRequest, OfferApiResponse(message = "Offer not created, invalid request"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid request: ${e.message}")
                        call.respondRedirect(UnauthorizedRoute.path)
                    }
                }
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.invalidSession(app: Application) {
    app.log.info("Invalid session")
    call.respondRedirect(UnauthorizedRoute.path)
}