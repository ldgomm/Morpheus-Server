package me.ldgomm.model.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Job(var enterprise: String? = null,
               var role: String? = null,
               var activities: List<Activity>? = null,
               var period: Period? = null)
