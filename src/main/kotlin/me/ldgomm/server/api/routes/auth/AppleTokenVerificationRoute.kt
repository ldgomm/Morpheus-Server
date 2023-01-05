package me.ldgomm.server.api.routes.auth

import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
import me.ldgomm.server.util.session.UserSession
import java.io.UnsupportedEncodingException
import java.util.*

fun Routing.appleTokenVerificationRoute(app: Application, authenticationRepositoriable: AuthenticationRepositoriable) {
    route(AppleTokenVerificationRoute.path) {
        post {
            val request: AuthenticationApiRequest = call.receive()
            if (request.idToken.isNotEmpty()) {
                val idToken: AppleIdTokenDecoder? = AppleIdTokenDecoder.getDecoded(request.idToken)
                if (idToken != null) {
                    val sub: String = idToken.sub!!
                    val name: String = idToken.name ?: "No name"
                    val email: String = idToken.email ?: "noemail@email.com"
                    val aud: String = idToken.aud!!
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

//Waiting key
class AppleIdTokenDecoder {
    var sub: String? = null
    var name: String? = null
    var email: String? = null
    val aud: String? = null
    override fun toString(): String {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)
    }

    companion object {
        @Throws(UnsupportedEncodingException::class)
        fun getDecoded(idToken: String): AppleIdTokenDecoder? {
            val pieces = idToken.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val payload = pieces[1]
            val decoder = Base64.getUrlDecoder()
            val jsonString = String(decoder.decode(payload))
            return Gson().fromJson(jsonString, AppleIdTokenDecoder::class.java)
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.createUserClient(app: Application,
                                                                            authenticationRepositoriable: AuthenticationRepositoriable,
                                                                            client: Client) {
    if (authenticationRepositoriable.createUserClient(client)) {
        app.log.info("User successfully saved")
        call.sessions.set(UserSession(idSession = client.idUser))
        call.respondRedirect(AuthorizedRoute.path)
    } else {
        unauthorizedRoute(app)
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.createUserPartner(app: Application,
                                                                             authenticationRepositoriable: AuthenticationRepositoriable,
                                                                             partner: Partner) {
    if (authenticationRepositoriable.createUserPartner(partner)) {
        app.log.info("User successfully saved")
        call.sessions.set(UserSession(idSession = partner.idUser))
        call.respondRedirect(AuthorizedRoute.path)
    } else {
        app.log.info("Error creating new user")
        call.respondRedirect(UnauthorizedRoute.path)
    }
}