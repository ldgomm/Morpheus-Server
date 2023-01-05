package me.ldgomm.server.util.constant

import me.ldgomm.model.entity.offer.*
import java.util.*

object Constants {
    //    const val audience = ""
    const val issuer = "https://accounts.google.com"

    val offer = Offer(idOffer = UUID.randomUUID().toString(),
                      title = "Title",
                      area = "Area",
                      schedule = "Schedule",
                      modality = "Modality",
                      location = "Location",
                      description = "Description",
                      preparation = Preparation(education = listOf(Education(area = "Area",
                                                                             degree = "Degree",
                                                                             level = "Level",
                                                                             specialization = "Specialization",
                                                                             certifications = listOf(Certification(name = "Name",
                                                                                                                   mandatory = false)),
                                                                             mandatory = true),
                                                                   Education(area = "Area", mandatory = false)),
                                                knowledge = listOf(Knowledge(name = "Name",
                                                                             description = "Description",
                                                                             mandatory = true)),
                                                experience = listOf(Experience(years = 2, mandatory = false))),
                      benefits = listOf(Benefit(name = "Benefit", description = "Description")),
                      wageRange = WageRange(minimum = 450, maximum = 650),
                      details = listOf(Detail(name = "Name", description = "Description")))
}