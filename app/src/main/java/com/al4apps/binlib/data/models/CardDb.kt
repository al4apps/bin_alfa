package com.al4apps.binlib.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.al4apps.binlib.domain.models.Bank
import com.al4apps.binlib.domain.models.Country

@Entity(tableName = CardDb.TABLE_NAME)
data class CardDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_BIN)
    val bin: Int,
    @ColumnInfo(name = COLUMN_NUMBER_LENGTH)
    val numberLength: Int?,
    @ColumnInfo(name = COLUMN_SCHEME)
    val scheme: String?,
    @ColumnInfo(name = COLUMN_TYPE)
    val type: String?,
    @ColumnInfo(name = COLUMN_BRAND)
    val brand: String?
) {
    companion object {
        const val TABLE_NAME = "card"
        const val COLUMN_ID = "id"
        const val COLUMN_BIN = "bin"
        const val COLUMN_NUMBER_LENGTH = "number_length"
        const val COLUMN_SCHEME = "scheme"
        const val COLUMN_TYPE = "type"
        const val COLUMN_BRAND = "brand"
    }
}

@Entity(tableName = CountryDb.TABLE_NAME)
data class CountryDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_CARD_ID)
    val cardId: Int,
    @ColumnInfo(name = COLUMN_NUMERIC)
    override val numeric: String?,
    @ColumnInfo(name = COLUMN_ALPHA2)
    override val alpha2: String?,
    @ColumnInfo(name = COLUMN_NAME)
    override val name: String?,
    @ColumnInfo(name = COLUMN_EMOJI)
    override val emoji: String?,
    @ColumnInfo(name = COLUMN_CURRENCY)
    override val currency: String?,
    @ColumnInfo(name = COLUMN_LATITUDE)
    override val latitude: Int?,
    @ColumnInfo(name = COLUMN_LONGITUDE)
    override val longitude: Int?
) : Country {

    companion object {
        const val TABLE_NAME = "country"
        const val COLUMN_ID = "id"
        const val COLUMN_CARD_ID = "card_id"
        const val COLUMN_NUMERIC = "numeric"
        const val COLUMN_ALPHA2 = "alpha2"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMOJI = "emoji"
        const val COLUMN_CURRENCY = "currency"
        const val COLUMN_LATITUDE = "latitude"
        const val COLUMN_LONGITUDE = "longitude"
    }
}

@Entity(tableName = BankDb.TABLE_NAME)
data class BankDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_CARD_ID)
    val cardId: Int,
    @ColumnInfo(name = COLUMN_NAME)
    override val name: String?,
    @ColumnInfo(name = COLUMN_URL)
    override val url: String?,
    @ColumnInfo(name = COLUMN_PHONE)
    override val phone: String?,
    @ColumnInfo(name = COLUMN_CITY)
    override val city: String?
) : Bank {

    companion object {
        const val TABLE_NAME = "bank"
        const val COLUMN_ID = "id"
        const val COLUMN_CARD_ID = "card_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_URL = "url"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_CITY = "city"
    }
}