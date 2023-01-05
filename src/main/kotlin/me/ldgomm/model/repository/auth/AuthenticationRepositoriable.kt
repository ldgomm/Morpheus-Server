package me.ldgomm.model.repository.auth

import me.ldgomm.model.entity.user.Client
import me.ldgomm.model.entity.user.Partner

sealed interface AuthenticationRepositoriable {
    suspend fun createUserClient(client: Client): Boolean
    suspend fun createUserPartner(partner: Partner): Boolean
}