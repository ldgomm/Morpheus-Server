package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Preparation(var education: List<Education>? = null,
                       var knowledge: List<Knowledge>? = null,
                       var experience: List<Experience>? = null)
