package me.ldgomm.server.util.constant

import me.ldgomm.model.entity.offer.*
import java.util.*

object Constants {
    //    const val audience = ""
    const val issuer = "https://accounts.google.com"

    val offer = Offer(idOffer = UUID.randomUUID().toString(),
                      title = "Programmer",
                      area = "Software",
                      schedule = "Morning",
                      modality = "Distance",
                      location = "Quito",
                      description = "Mobile programmer",
//                      preparation = Preparation(education = listOf(Education(area = "Software",
//                                                                             degree = "Engineering",
//                                                                             level = "Third",
//                                                                             specialization = "Mobile Applications",
//                                                                             certifications = listOf(Certification(name = "Java",
//                                                                                                                   area = "Programming",
//                                                                                                                   description = "Java SE 8",
//                                                                                                                   offerer = "Oracle",
//                                                                                                                   site = "oracle.com",
//                                                                                                                   year = "2018",
//                                                                                                                   mandatory = false)),
//                                                                             mandatory = true)),
//                                                knowledge = listOf(Knowledge(name = "Java",
//                                                                             description = "Java 11",
//                                                                             mandatory = true)),
//                                                experience = listOf(Experience(years = 2, mandatory = true))),
//                      benefits = listOf(Benefit(name = "Name", description = "null")),
//                      wageRange = WageRange(minimum = 1000, maximum = 2000, currency = "USD"),
//                      details = listOf(Detail(name = "Salary", description = "No bad")),
//                      publisher = "null",
//                      publishedOn = System.currentTimeMillis() / 1000,
//                      consumers = listOf("null"),
                      isActive = true,
                      timestamp = System.currentTimeMillis() / 1000)
}