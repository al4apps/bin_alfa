package com.al4apps.binlib.data.repositories

import android.util.Log
import com.al4apps.binlib.data.datasource.DbSource
import com.al4apps.binlib.data.datasource.NetworkDataSource
import com.al4apps.binlib.data.models.BankDto
import com.al4apps.binlib.data.models.CardDto
import com.al4apps.binlib.data.models.CountryDto
import com.al4apps.binlib.data.models.NumberDto
import com.al4apps.binlib.domain.models.BankModel
import com.al4apps.binlib.domain.models.CardModel
import com.al4apps.binlib.domain.models.CountryModel
import com.al4apps.binlib.domain.models.NumberModel
import com.al4apps.binlib.domain.repositories.CardsRepository
import kotlinx.coroutines.flow.Flow

class CardsRepositoryImpl(
    private val dataSource: NetworkDataSource,
    private val dbSource: DbSource
) : CardsRepository {
    override suspend fun getCardInfo(number: Int): CardModel {
        val card = dataSource.getCardInfo(number)
        dbSource.saveCard(card)
        return card.toCardModel()
    }

    override fun savedCards(): Flow<List<CardModel>> {
        return dbSource.savedCards()
    }


    private fun CardDto.toCardModel(): CardModel {
        return CardModel(
            number = number?.toNumberModel(),
            scheme = scheme,
            type = type,
            brand = brand,
            country = country?.toCountryModel(),
            bank = bank?.toBankModel(),
        )
    }

    private fun NumberDto.toNumberModel(): NumberModel {
        return NumberModel(
            length = length
        )
    }

    private fun CountryDto.toCountryModel(): CountryModel {
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

    private fun BankDto.toBankModel(): BankModel {
        return BankModel(
            name = name,
            url = url,
            phone = phone,
            city = city
        )
    }
}