package me.ldgomm.model.repository.offer

import me.ldgomm.model.entity.offer.Offer
import org.litote.kmongo.coroutine.CoroutineDatabase

class OfferRepository(db: CoroutineDatabase) : OfferRepositoriable {
    private val offerCollection = db.getCollection<Offer>()

    override suspend fun createOffer(offer: Offer): Boolean {
        return offerCollection.insertOne(offer).wasAcknowledged()
    }

    override suspend fun readOffers(): List<Offer> {
        return offerCollection.find().descendingSort(Offer::timestamp).toList()
    }
}