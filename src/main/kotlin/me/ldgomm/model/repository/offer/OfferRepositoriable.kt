package me.ldgomm.model.repository.offer

import me.ldgomm.model.entity.offer.Offer

sealed interface OfferRepositoriable {
    suspend fun createOffer(offer: Offer): Boolean

    suspend fun readOffers(): List<Offer>
}