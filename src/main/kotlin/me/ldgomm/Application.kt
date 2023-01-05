package me.ldgomm

import io.ktor.server.application.*
import me.ldgomm.server.util.plugins.*
import me.ldgomm.server.util.plugins.configureMonitoring
import me.ldgomm.server.util.plugins.configureSerialization

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureAuthentication()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureSecurity()
}
