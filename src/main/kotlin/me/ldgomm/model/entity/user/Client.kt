package me.ldgomm.model.entity.user

import kotlinx.serialization.Serializable

@Serializable
data class Client(val idUser: String,
                  var name: String,
                  val email: String,
                  val aud: String)
//                  var userPersonalInformation: UserMainInformation? = null,
//                  var userProfessionalInformation: UserProfessionalInformation? = null,
//                  var userSystemStatusInformation: UserSystemStatusInformation? = null,
//                  val timestamp: Long = System.currentTimeMillis() / 1000)