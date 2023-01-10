package me.ldgomm.model.repository.offer

import me.ldgomm.model.entity.offer.Offer
import me.ldgomm.model.entity.user.Client
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class OfferRepository(db: CoroutineDatabase) : OfferRepositoriable {
    private val offerCollection = db.getCollection<Offer>()

    override suspend fun createOffer(offer: Offer): Boolean {
        return if (offerCollection.findOne(filter = Offer::idOffer eq offer.idOffer) == null) {
            offerCollection.insertOne(offer).wasAcknowledged()
        } else {
            true
        }
    }

    override suspend fun readOffers(): List<Offer>? {
        return offerCollection.find().descendingSort(Offer::timestamp).toList()
    }

    override suspend fun updateOffer(offer: Offer): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOffer(offer: Offer): Boolean {
        return offerCollection.deleteOne(filter = Offer::idOffer eq offer.idOffer).wasAcknowledged()
    }
}