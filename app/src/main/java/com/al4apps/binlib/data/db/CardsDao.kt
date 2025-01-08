package com.al4apps.binlib.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.al4apps.binlib.data.models.BankDb
import com.al4apps.binlib.data.models.CardDb
import com.al4apps.binlib.data.models.CountryDb
import kotlinx.coroutines.flow.Flow

@Dao
interface CardsDao {

    @Query("SELECT * FROM ${CardDb.TABLE_NAME} ORDER BY ${CardDb.COLUMN_TIMESTAMP} DESC")
    fun cards(): Flow<List<CardDb>>

    @Query("SELECT * FROM ${CountryDb.TABLE_NAME} WHERE ${CountryDb.COLUMN_CARD_ID} = :cardId")
    suspend fun getCountryDbByCardId(cardId: Int): CountryDb

    @Query("SELECT * FROM ${BankDb.TABLE_NAME} WHERE ${BankDb.COLUMN_CARD_ID} = :cardId")
    suspend fun getBankDbByCardId(cardId: Int): BankDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCard(card: CardDb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCountry(country: CountryDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBank(bank: BankDb)
}