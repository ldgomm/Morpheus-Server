package me.ldgomm.model.entity.auth

import kotlinx.serialization.Serializable
import me.ldgomm.model.entity.user.Client
import me.ldgomm.model.entity.user.Partner

@Serializable
data class AuthenticationApiResponse(val success: Boolean,
                                     val message: String? = null,
                                     val client: Client? = null,
                                     val partner: Partner? = null)
