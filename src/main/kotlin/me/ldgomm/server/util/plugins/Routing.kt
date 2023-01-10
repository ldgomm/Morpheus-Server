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
                call.respond(OK,
                             Offer(title = "Programmer",
                                   area = "Software",
                                   schedule = "Morning",
                                   modality = "Distance",
                                   location = "Quito",
                                   description = "Mobile programmer",
                                   preparation = Preparation(education = listOf(Education(area = "Software",
                                                                                          degree = "Engineering",
                                                                                          level = "Third",
                                                                                          specialization = "Mobile Applications",
                                                                                          certifications = listOf(
                                                                                              Certification(name = "Java",
                                                                                                            area = "Programming",
                                                                                                            description = "Java SE 8",
                                                                                                            offerer = "Oracle",
                                                                                                            site = "oracle.com",
                                                                                                            year = "2018",
                                                                                                            mandatory = false)),
                                                                                          mandatory = true)),
                                                             knowledge = listOf(Knowledge(name = "Java",
                                                                                          description = "Java 11",
                                                                                          mandatory = true)),
                                                             experience = listOf(Experience(years = 2,
                                                                                            mandatory = true))),
                                   benefits = listOf(Benefit(name = "Name", description = "null")),
                                   wageRange = WageRange(minimum = 1000, maximum = 2000, currency = "USD"),
                                   details = listOf(Detail(name = "Salary", description = "No bad")),
                                   publisher = "null",
                                   publishedOn = System.currentTimeMillis() / 1000,
                                   consumers = listOf("null"),
                                   isActive = true,
                                   timestamp = System.currentTimeMillis() / 1000))
            }
        }
    }
}
