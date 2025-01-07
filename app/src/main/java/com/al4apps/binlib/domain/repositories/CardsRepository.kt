package com.al4apps.binlib.domain.repositories

import com.al4apps.binlib.domain.models.CardModel
import kotlinx.coroutines.flow.Flow

interface CardsRepository {

    suspend fun getCardInfo(number: Int): CardModel

    fun savedCards(): Flow<List<CardModel>>
}