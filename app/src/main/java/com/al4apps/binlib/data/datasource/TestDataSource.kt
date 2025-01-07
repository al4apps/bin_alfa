package com.al4apps.binlib.data.datasource

import com.al4apps.binlib.data.models.BankDto
import com.al4apps.binlib.data.models.CardDto
import com.al4apps.binlib.data.models.CountryDto
import com.al4apps.binlib.data.models.NumberDto

class TestDataSource: DataSource {
    override suspend fun getCardInfo(number: Int): CardDto {
        return CardDto(
            number = NumberDto(
                length = 16,
            ),
            scheme = "visa",
            type = "debit",
            brand = "Visa/Dankort",
            country = CountryDto(
                numeric = "208",
                alpha2 = "DK",
                name = "Denmark",
                emoji = "🇩🇰",
                currency = "DKK",
                latitude = 56,
                longitude = 10
            ),
            bank = BankDto(
                name = "Jyske Bank",
                url = "www.jyskebank.dk",
                phone = "+4589893300",
                city = "Hjørring"
            )
        )


    }
}