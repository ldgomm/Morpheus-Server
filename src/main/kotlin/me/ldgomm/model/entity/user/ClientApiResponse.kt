package me.ldgomm.model.entity.user

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

//
//  UserApiRequest.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

@Serializable
data class ClientApiResponse(val success: Boolean,
                             val message: String? = null,
                             val client: Client? = null,
                             @Transient val error: Exception? = null)