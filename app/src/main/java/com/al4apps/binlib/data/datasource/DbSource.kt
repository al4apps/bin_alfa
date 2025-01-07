package com.al4apps.binlib.data.datasource

import com.al4apps.binlib.data.db.CardsDao
import com.al4apps.binlib.data.models.BankDb
import com.al4apps.binlib.data.models.CardDb
import com.al4apps.binlib.data.models.CountryDb
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
                val country = dao.getCountryDbByCardId(it.id)
                val bank = dao.getBankDbByCardId(it.id)
                it.toCardModel(country, bank)
            }
        }
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