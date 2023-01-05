package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Period(var from: Long? = null, val to: Long? = null)
