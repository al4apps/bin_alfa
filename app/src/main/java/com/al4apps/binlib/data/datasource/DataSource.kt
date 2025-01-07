package com.al4apps.binlib.data.datasource

import com.al4apps.binlib.data.models.CardDto
import com.al4apps.binlib.domain.models.Card

interface DataSource {

    suspend fun getCardInfo(number: Int): Card
}