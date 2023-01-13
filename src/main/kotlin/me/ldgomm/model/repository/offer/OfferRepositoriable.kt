package me.ldgomm.model.repository.offer

import me.ldgomm.model.entity.offer.Offer

sealed interface OfferRepositoriable {
    suspend fun createOffer(offer: Offer): Boolean

    suspend fun readOffers(): List<Offer>?

    suspend fun readOffer(offer: Offer): Offer?

    suspend fun updateOffer(offer: Offer): Boolean

    suspend fun deleteOffer(offer: Offer): Boolean
}