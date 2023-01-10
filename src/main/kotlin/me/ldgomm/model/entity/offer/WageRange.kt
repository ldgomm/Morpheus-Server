package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class WageRange(var minimum: Int? = null, var maximum: Int? = null, var currency: String = "USD")
