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
                      description = "Mobile programmer",
                      location = Location(latitude = 0.0, longitude = 0.0),
                      preparation = Preparation(education = listOf(Education(area = "Software",
                                                                             degree = "Engineering",
                                                                             level = "Third",
                                                                             specialization = "Mobile Applications",
                                                                             certifications = listOf(Certification(name = "Java",
                                                                                                                   area = "Programming",
                                                                                                                   description = "Java SE 8",
                                                                                                                   offerer = "Oracle",
                                                                                                                   site = "oracle.com",
                                                                                                                   year = "2018",
                                                                                                                   mandatory = true)),
                                                                             mandatory = true)),
                                                knowledge = listOf(Knowledge(name = "Java",
                                                                             description = "Java 11",
                                                                             mandatory = true)),
                                                experience = listOf(Experience(years = 2, mandatory = true))),
                      benefits = listOf(Benefit(name = "Name", description = "null")),
                      wageRange = WageRange(minimum = 1000, maximum = 2000, currency = "USD"),
                      details = listOf(Detail(name = "Salary", description = "No bad")),
                      publisher = "null",
                      consumers = listOf("null"),
                      isActive = true,
                      timestamp = System.currentTimeMillis() / 1000)
}

//@HiltViewModel
//class OfferViewModel : ViewModel() {
//    var id: String = UUID.randomUUID().toString()
//    var title: String by mutableStateOf("")
//    val area: String by mutableStateOf("")
//    val schedule: String by mutableStateOf("")
//    val modality: String by mutableStateOf("")
//    val description: String by mutableStateOf("")
//    val location: Location? by mutableStateOf(Location())
//    var preparation: Preparation? by mutableStateOf(Preparation())
//    val benefits: List<Benefit>? by mutableStateOf(listOf())
//    val wageRange: WageRange? by mutableStateOf(WageRange())
//    val details: List<Detail>? by mutableStateOf(listOf())
//    val publisher: String? by mutableStateOf("")
//    val consumers: List<String>? by mutableStateOf(listOf())
//    val isActive: Boolean by mutableStateOf(false)
//    val timestamp: Long = System.currentTimeMillis() / 1000
//
//    init {
//        preparation = Preparation(education = listOf(Education()))
//    }
//}