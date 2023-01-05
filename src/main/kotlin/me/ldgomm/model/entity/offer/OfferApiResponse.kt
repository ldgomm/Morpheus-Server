package me.ldgomm.model.entity.offer

data class OfferApiResponse(var success: Boolean = false,
                            var message: String? = null,
                            var offer: Offer? = null,
                            var offers: List<Offer>? = null)
