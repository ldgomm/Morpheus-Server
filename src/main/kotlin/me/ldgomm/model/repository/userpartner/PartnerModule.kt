package me.ldgomm.model.repository.userpartner

import org.koin.dsl.module

val partnerRepositoryModule = module {
    single<PartnerRepositoriable> {
        PartnerRepository(get())
    }
}