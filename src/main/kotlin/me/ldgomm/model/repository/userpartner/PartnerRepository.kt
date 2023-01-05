package me.ldgomm.model.repository.userpartner

import me.ldgomm.model.entity.user.Client
import me.ldgomm.model.entity.user.Partner
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PartnerRepository(db: CoroutineDatabase) : PartnerRepositoriable {
    private val partnerCollection = db.getCollection<Partner>()

    override suspend fun readPartner(idPartner: String): Partner? {
        return partnerCollection.findOne(filter = Partner::idUser eq idPartner)
    }

    override suspend fun updatePartner(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deletePartner(idPartner: String): Boolean {
        return partnerCollection.deleteOne(filter = Client::idUser eq idPartner).wasAcknowledged()
    }
}