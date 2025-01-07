package com.al4apps.binlib.data.db

import androidx.room.Dao
import androidx.room.Query
import com.al4apps.binlib.data.models.BankDb
import com.al4apps.binlib.data.models.CardDb
import com.al4apps.binlib.data.models.CountryDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CardsDao {

    @Query("SELECT * FROM ${CardDb.TABLE_NAME}")
    fun cards(): Flow<List<CardDb>>

    @Query("SELECT * FROM ${CardDb.TABLE_NAME} WHERE ${CardDb.COLUMN_BIN} = :bin")
    suspend fun getCardDbByBin(bin: Int): CardDb

    @Query("SELECT * FROM ${CountryDb.TABLE_NAME} WHERE ${CountryDb.COLUMN_CARD_ID} = :cardId")
    suspend fun getCountryDbByCardId(cardId: Int): CountryDb

    @Query("SELECT * FROM ${BankDb.TABLE_NAME} WHERE ${BankDb.COLUMN_CARD_ID} = :cardId")
    suspend fun getBankDbByCardId(cardId: Int): BankDb
}