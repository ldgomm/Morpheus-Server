package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Activity(var position: String? = null, var description: String? = null)
