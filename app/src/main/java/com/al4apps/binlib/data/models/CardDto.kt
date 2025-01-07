package com.al4apps.binlib.data.models

import com.al4apps.binlib.domain.models.Bank
import com.al4apps.binlib.domain.models.Card
import com.al4apps.binlib.domain.models.Country
import com.al4apps.binlib.domain.models.Number
import com.google.gson.annotations.SerializedName

data class CardDto(
    @SerializedName("number") override val number: NumberDto?,
    @SerializedName("scheme") override val scheme: String?,
    @SerializedName("type") override val type: String?,
    @SerializedName("brand") override val brand: String?,
    @SerializedName("country") override val country: CountryDto?,
    @SerializedName("bank") override val bank: BankDto?
) : Card

data class NumberDto(
    @SerializedName("length") override val length: Int?,
) : Number

data class CountryDto(
    @SerializedName("numeric") override val numeric: String?,
    @SerializedName("alpha2") override val alpha2: String?,
    @SerializedName("name") override val name: String?,
    @SerializedName("emoji") override val emoji: String?,
    @SerializedName("currency") override val currency: String?,
    @SerializedName("latitude") override val latitude: Int?,
    @SerializedName("longitude") override val longitude: Int?
) : Country

data class BankDto(
    @SerializedName("name") override val name: String?,
    @SerializedName("latitude") override val url: String?,
    @SerializedName("latitude") override val phone: String?,
    @SerializedName("latitude") override val city: String?
) : Bank