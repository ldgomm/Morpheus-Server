package me.ldgomm.model.repository.offer

import org.koin.dsl.module

val offerRepositoryModule = module {
    single<OfferRepositoriable> {
        OfferRepository(get())
    }
}