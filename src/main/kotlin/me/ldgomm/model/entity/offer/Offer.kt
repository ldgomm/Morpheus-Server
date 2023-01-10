package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Offer(val idOffer: String,
                 val title: String,
                 val area: String,
                 val schedule: String,
                 val modality: String,
                 val location: String,
                 val description: String,
                 val preparation: Preparation? = null,
                 val benefits: List<Benefit>? = null,
                 val wageRange: WageRange? = null,
                 val details: List<Detail>? = null,
                 val publisher: String? = null,
                 val publishedOn: Long? = null,
                 val consumers: List<String>? = null,
                 val isActive: Boolean = false,
                 val timestamp: Long)