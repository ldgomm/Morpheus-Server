package me.ldgomm.model.repository.auth

import me.ldgomm.model.entity.user.Client
import me.ldgomm.model.entity.user.Partner
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class AuthenticationRepository(db: CoroutineDatabase) : AuthenticationRepositoriable {
    private val clientCollection = db.getCollection<Client>()
    private val partnerCollection = db.getCollection<Partner>()
    override suspend fun createUserClient(client: Client): Boolean {
        return if (clientCollection.findOne(filter = Client::idUser eq client.idUser) == null) {
            clientCollection.insertOne(client).wasAcknowledged()
        } else {
            true
        }
    }

    override suspend fun createUserPartner(partner: Partner): Boolean {
        return if (partnerCollection.findOne(filter = Partner::idUser eq partner.idUser) == null) {
            partnerCollection.insertOne(partner).wasAcknowledged()
        } else {
            true
        }
    }
}