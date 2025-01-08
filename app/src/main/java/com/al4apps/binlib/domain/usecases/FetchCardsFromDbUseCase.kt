package com.al4apps.binlib.domain.usecases

import com.al4apps.binlib.data.repositories.CardsRepositoryImpl
import com.al4apps.binlib.domain.models.CardModel
import kotlinx.coroutines.flow.Flow

class FetchCardsFromDbUseCase(
    private val cardsRepository: CardsRepositoryImpl
) {
    fun flow(): Flow<List<CardModel>> {
        return cardsRepository.savedCards()
    }
}