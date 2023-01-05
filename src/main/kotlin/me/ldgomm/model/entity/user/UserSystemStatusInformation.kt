package me.ldgomm.model.entity.user

import kotlinx.serialization.Serializable

@Serializable
data class UserSystemStatusInformation(val isValidated: Boolean = false,
                                       val isActive: Boolean = true,
                                       val isSuspended: Boolean = false,
                                       val isBlocked: Boolean = false)