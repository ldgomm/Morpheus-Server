package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Certification(var name: String? = null,
                         var area: String? = null,
                         var description: String? = null,
                         var offerer: String? = null,
                         var site: String? = null,
                         var year: String? = null,
                         var mandatory: Boolean = false)
