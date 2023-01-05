package me.ldgomm.server.util.plugins

import io.ktor.server.application.*
import me.ldgomm.model.database.morpheusDatabaseModule
import me.ldgomm.model.repository.auth.authenticationRepositoryModule
import me.ldgomm.model.repository.offer.offerRepositoryModule
import me.ldgomm.model.repository.userclient.clientRepositoryModule
import me.ldgomm.model.repository.userpartner.partnerRepositoryModule
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(morpheusDatabaseModule,
                authenticationRepositoryModule,
                clientRepositoryModule,
                partnerRepositoryModule,
                offerRepositoryModule)
    }
}