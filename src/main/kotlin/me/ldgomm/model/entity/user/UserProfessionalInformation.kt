package me.ldgomm.model.entity.user

import kotlinx.serialization.Serializable
import me.ldgomm.model.entity.offer.Preparation

@Serializable
data class UserProfessionalInformation(var preparation: Preparation? = null,
                                       var wageAspiration: Int? = null,
                                       var category: String? = null,
                                       var location: String? = null)