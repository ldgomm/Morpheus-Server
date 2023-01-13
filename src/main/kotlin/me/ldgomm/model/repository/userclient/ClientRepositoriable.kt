package me.ldgomm.model.repository.userclient

import me.ldgomm.model.entity.user.Client

sealed interface ClientRepositoriable {
    suspend fun readClient(idClient: String): Client

    suspend fun updateClient(name: String): Boolean

    suspend fun deleteClient(idClient: String): Boolean
}