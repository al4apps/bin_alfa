package com.al4apps.binlib.data.datasource

import android.util.Log
import com.al4apps.binlib.data.db.CardsDao
import com.al4apps.binlib.data.models.BankDb
import com.al4apps.binlib.data.models.BankDto
import com.al4apps.binlib.data.models.CardDb
import com.al4apps.binlib.data.models.CardDto
import com.al4apps.binlib.data.models.CountryDb
import com.al4apps.binlib.data.models.CountryDto
import com.al4apps.binlib.domain.models.BankModel
import com.al4apps.binlib.domain.models.CardModel
import com.al4apps.binlib.domain.models.CountryModel
import com.al4apps.binlib.domain.models.NumberModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DbSource(
    private val dao: CardsDao
) {

    fun savedCards(): Flow<List<CardModel>> {
        return dao.cards().map { list ->
            list.map {
                val country = it.id?.let { it1 -> dao.getCountryDbByCardId(it1) }
                val bank = it.id?.let { it1 -> dao.getBankDbByCardId(it1) }
                it.toCardModel(country, bank)
            }
        }
    }

    suspend fun saveCard(card: CardDto) {
        card.toCardDb().let { cardDb ->
            val cardId = dao.saveCard(cardDb)
            card.country?.toCountryDb(cardId.toInt())?.let { countryDb ->
                dao.saveCountry(countryDb)
            }
            card.bank?.toBankDb(cardId.toInt())?.let { bankDb ->
                dao.saveBank(bankDb)
            }
        }
    }

    private fun CardDto.toCardDb(): CardDb {
        return CardDb(
            numberLength = number?.length,
            scheme = scheme,
            type = type,
            brand = brand,
            timestamp = System.currentTimeMillis()
        )
    }

    private fun CountryDto.toCountryDb(cardId: Int): CountryDb {
        return CountryDb(
            numeric = numeric,
            alpha2 = alpha2,
            name = name,
            emoji = emoji,
            currency = currency,
            latitude = latitude,
            longitude = longitude,
            cardId = cardId
        )
    }

    private fun BankDto.toBankDb(cardId: Int): BankDb {
        return BankDb(
            name = name,
            url = url,
            phone = phone,
            city = city,
            cardId = cardId,
        )
    }

    private fun CardDb.toCardModel(country: CountryDb?, bank: BankDb?): CardModel {
        return CardModel(
            number = NumberModel(numberLength),
            scheme = scheme,
            type = type,
            brand = brand,
            country = country?.toCountryModel(),
            bank = bank?.toBankModel(),
        )
    }

    private fun CountryDb.toCountryModel(): CountryModel {
        return CountryModel(
            numeric = numeric,
            alpha2 = alpha2,
            name = name,
            emoji = emoji,
            currency = currency,
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun BankDb.toBankModel(): BankModel {
        return BankModel(
            name = name,
            url = url,
            phone = phone,
            city = city,
        )
    }
}