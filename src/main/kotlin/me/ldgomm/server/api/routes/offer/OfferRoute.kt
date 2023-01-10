package me.ldgomm.server.api.routes.offer

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Conflict
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.ldgomm.model.entity.offer.Offer
import me.ldgomm.model.entity.offer.OfferApiRequest
import me.ldgomm.model.entity.offer.OfferApiResponse
import me.ldgomm.model.repository.offer.OfferRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.OfferRoute
import me.ldgomm.server.api.endpoints.Endpoint.UnauthorizedRoute
import me.ldgomm.server.util.constant.Constants.offer
import me.ldgomm.server.util.extension.invalidSession
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
                        val offers: List<Offer>? = offerRepositoriable.readOffers()
                        if (offers != null) {
                            if (offers.isNotEmpty()) {
                                call.respond(OK, message = OfferApiResponse(success = true, offers = offers))
                            } else {
                                call.respond(OK,
                                             message = OfferApiResponse(success = true,
                                                                        message = "No offers found",
                                                                        offers = listOf(offer)))
                            }
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid request: ${e.message}")
                        call.respond(BadRequest,
                                     message = OfferApiResponse(success = false,
                                                                message = e.message,
                                                                offers = listOf(offer)))
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
                        if (offerRepositoriable.createOffer(offer = request.offer)) {
                            call.respond(Created, OfferApiResponse(success = true, offer = request.offer))
                        } else {
                            call.respond(Conflict,
                                         OfferApiResponse(success = true,
                                                          message = "Offer not created, invalid request"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid request: ${e.message}")
                        call.respond(BadRequest, OfferApiResponse(success = false, message = e.message))
                    }
                }
            }

            put {

            }

            delete {
                val userSession: UserSession? = call.principal()
                if (userSession == null) {
                    invalidSession(app)
                } else {
                    try {
                        val request: OfferApiRequest = call.receive()
                        if (offerRepositoriable.deleteOffer(request.offer)) {
                            call.respond(OK, OfferApiResponse(success = true, message = "Offer deleted"))
                        } else {
                            call.respond(NotFound, OfferApiResponse(success = false, "No offer found"))
                        }
                    } catch (e: Exception) {
                        app.log.info("Invalid deleting offer: ${e.message}")
                        call.respond(BadRequest, OfferApiResponse(success = false, message = "Error deleting offer"))
                    }
                }
            }
        }
    }
}