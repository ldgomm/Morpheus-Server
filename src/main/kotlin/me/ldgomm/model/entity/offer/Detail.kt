package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Detail(var name: String? = null, var description: String? = null)
