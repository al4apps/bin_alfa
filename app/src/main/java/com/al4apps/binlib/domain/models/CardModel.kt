package com.al4apps.binlib.domain.models

data class CardModel(
    override val number: NumberModel?,
    override val scheme: String?,
    override val type: String?,
    override val brand: String?,
    override val country: CountryModel?,
    override val bank: BankModel?

): Card

data class NumberModel(override val length: Int?) : Number

data class CountryModel(
    override val numeric: String?,
    override val alpha2: String?,
    override val name: String?,
    override val emoji: String?,
    override val currency: String?,
    override val latitude: Int?,
    override val longitude: Int?
) : Country

data class BankModel(
    override val name: String?,
    override val url: String?,
    override val phone: String?,
    override val city: String?
) : Bank
