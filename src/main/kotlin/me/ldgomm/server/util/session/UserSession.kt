package me.ldgomm.server.util.session

import io.ktor.server.auth.*

data class UserSession(val idSession: String) : Principal
