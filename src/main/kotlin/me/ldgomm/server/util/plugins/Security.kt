package me.ldgomm.server.util.plugins

import io.ktor.server.sessions.*
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import me.ldgomm.server.util.session.UserSession
import java.io.File
import kotlin.time.Duration.Companion.minutes

fun Application.configureSecurity() {
    install(Sessions) {
        val secretEncryptKey: ByteArray = hex("00112233445566778899aabbccddeeff")
        val secretAuthKey: ByteArray = hex("02030405060708090a0b0c")
        cookie<UserSession>(name = "USER_SESSION", storage = directorySessionStorage(File(".sessions"))) {
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretAuthKey))
        }
    }
}
