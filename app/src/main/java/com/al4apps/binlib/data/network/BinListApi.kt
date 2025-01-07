package com.al4apps.binlib.data.network

import com.al4apps.binlib.data.models.CardDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BinListApi {

    @GET("{number}")
    suspend fun getCardInfo(@Path("number") number: Int): CardDto

}