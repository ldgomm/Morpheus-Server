package me.ldgomm.model.entity.offer

data class OfferApiResponse(val success: Boolean = false,
                            val message: String? = null,
                            val offer: Offer? = null,
                            val offers: List<Offer>? = null)
