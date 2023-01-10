package me.ldgomm.server.api.routes.auth

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import me.ldgomm.model.entity.auth.AuthenticationApiRequest
import me.ldgomm.model.entity.user.Client
import me.ldgomm.model.entity.user.Partner
import me.ldgomm.model.repository.auth.AuthenticationRepositoriable
import me.ldgomm.server.api.endpoints.Endpoint.*
import me.ldgomm.server.util.constant.Constants.issuer
import me.ldgomm.server.util.extension.unauthorizedRoute
import me.ldgomm.server.util.session.UserSession

fun Routing.googleTokenVerificationRoute(app: Application, authenticationRepositoriable: AuthenticationRepositoriable) {
    route(GoogleTokenVerificationRoute.path) {
        post {
            val request: AuthenticationApiRequest = call.receive()
            if (request.idToken.isNotEmpty()) {
                val idToken: GoogleIdToken? = googleIdTokenVerifier(request.idToken)
                if (idToken != null) {
                    val sub: String = idToken.payload["sub"].toString()
                    val name: String = idToken.payload["name"].toString()
                    val email: String = idToken.payload["email"].toString()
                    val aud: String = idToken.payload["aud"].toString()

                    if (request.prime < 7) {
                        val client = Client(sub, name, email, aud)
                        createUserClient(app, authenticationRepositoriable, client)
                    } else {
                        val partner = Partner(sub, name, email, aud)
                        createUserPartner(app, authenticationRepositoriable, partner)
                    }
                } else {
                    app.log.info("Token verification failed")
                    call.respondRedirect(UnauthorizedRoute.path)
                }
            } else {
                app.log.info("Empty token id")
                call.respondRedirect(UnauthorizedRoute.path)
            }
        }
    }
}

fun googleIdTokenVerifier(idToken: String): GoogleIdToken? {
    return try {
        val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
            .setAudience(listOf(System.getenv("audience"))).setIssuer(issuer).build()
        verifier.verify(idToken)
    } catch (e: Exception) {
        null
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.createUserClient(app: Application,
                                                                            authenticationRepositoriable: AuthenticationRepositoriable,
                                                                            client: Client) {
    app.log.info("Token successfully verified")
    if (authenticationRepositoriable.createUserClient(client)) {
        app.log.info("User successfully saved")
        call.sessions.set(UserSession(client.idUser))
        call.respondRedirect(AuthorizedRoute.path)
    } else {
        unauthorizedRoute(app)
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.createUserPartner(app: Application,
                                                                             authenticationRepositoriable: AuthenticationRepositoriable,
                                                                             partner: Partner) {
    app.log.info("Token successfully verified")
    if (authenticationRepositoriable.createUserPartner(partner)) {
        app.log.info("User successfully saved")
        call.sessions.set(UserSession(partner.idUser))
        call.respondRedirect(AuthorizedRoute.path)
    } else {
        unauthorizedRoute(app)
    }
}