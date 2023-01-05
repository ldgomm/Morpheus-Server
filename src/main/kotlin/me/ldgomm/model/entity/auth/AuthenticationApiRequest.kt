package me.ldgomm.model.entity.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationApiRequest(val idToken: String, val prime: Int)
