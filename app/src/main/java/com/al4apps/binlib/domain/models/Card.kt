package com.al4apps.binlib.domain.models

interface Card {
    val number: Number?
    val scheme: String?
    val type: String?
    val brand: String?
    val country: Country?
    val bank: Bank?
}

interface Number {
    val length: Int?
}

interface Country {
    val numeric: String?
    val alpha2: String?
    val name: String?
    val emoji: String?
    val currency: String?
    val latitude: Int?
    val longitude: Int?
}

interface Bank {
    val name: String?
    val url: String?
    val phone: String?
    val city: String?
}
