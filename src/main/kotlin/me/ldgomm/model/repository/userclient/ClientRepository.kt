package me.ldgomm.model.repository.userclient

import me.ldgomm.model.entity.user.Client
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ClientRepository(db: CoroutineDatabase) : ClientRepositoriable {
    private val clientCollection = db.getCollection<Client>()

    override suspend fun readClient(idClient: String): Client {
        return clientCollection.findOne(filter = Client::idUser eq idClient)!!
    }

    override suspend fun updateClient(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteClient(idClient: String): Boolean {
        return clientCollection.deleteOne(filter = Client::idUser eq idClient).wasAcknowledged()
    }
}