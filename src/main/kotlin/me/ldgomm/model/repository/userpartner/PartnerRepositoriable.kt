package me.ldgomm.model.repository.userpartner

import me.ldgomm.model.entity.user.Partner

sealed interface PartnerRepositoriable {
    suspend fun readPartner(idPartner: String): Partner?

    suspend fun updatePartner(name: String): Boolean

    suspend fun deletePartner(idPartner: String): Boolean
}