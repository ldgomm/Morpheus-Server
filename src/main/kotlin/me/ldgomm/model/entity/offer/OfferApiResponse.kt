package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class OfferApiResponse(val success: Boolean,
                            val message: String? = null,
                            val offer: Offer? = null,
                            val offers: List<Offer>? = null)
