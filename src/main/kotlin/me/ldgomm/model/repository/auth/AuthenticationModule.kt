package me.ldgomm.model.repository.auth

import org.koin.dsl.module

val authenticationRepositoryModule = module {
    single<AuthenticationRepositoriable> {
        AuthenticationRepository(get())
    }
}