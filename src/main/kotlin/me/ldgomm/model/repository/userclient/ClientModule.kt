package me.ldgomm.model.repository.userclient

import org.koin.dsl.module

val clientRepositoryModule = module {
    single<ClientRepositoriable> {
        ClientRepository(get())
    }
}