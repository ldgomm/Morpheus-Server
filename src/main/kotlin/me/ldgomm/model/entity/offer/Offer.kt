package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Offer(var idOffer: String = UUID.randomUUID().toString(),
                 var title: String,
                 var area: String,
                 var schedule: String,
                 var modality: String,
                 var location: String,
                 var description: String,
                 var preparation: Preparation? = null,
                 var benefits: List<Benefit>? = null,
                 var wageRange: WageRange? = null,
                 var details: List<Detail>? = null,
                 var publisher: String? = null,
                 var consumers: List<String>? = null,
                 var timestamp: Long = System.currentTimeMillis() / 1000)