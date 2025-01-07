package com.al4apps.binlib.domain.usecases

import com.al4apps.binlib.data.repositories.CardsRepositoryImpl
import com.al4apps.binlib.domain.models.CardModel

class FetchCardInfoUseCase(
    private val cardsRepository: CardsRepositoryImpl
) {
    suspend fun execute(number: Int): CardModel {
        return cardsRepository.getCardInfo(number)
    }
}