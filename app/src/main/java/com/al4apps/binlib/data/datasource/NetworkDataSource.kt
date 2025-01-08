package com.al4apps.binlib.data.datasource

import com.al4apps.binlib.data.models.CardDto
import com.al4apps.binlib.data.network.BinListApi

class NetworkDataSource(
    private val binListApi: BinListApi
) : DataSource {
    override suspend fun getCardInfo(number: Int): CardDto {
        return binListApi.getCardInfo(number.toString())
    }
}