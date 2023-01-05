package me.ldgomm.model.database

import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val morpheusDatabaseModule = module {
    single { KMongo.createClient(System.getenv("mongo_uri")).coroutine.getDatabase("morpheus_database") }
}