package com.al4apps.binlib.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.al4apps.binlib.data.models.BankDb
import com.al4apps.binlib.data.models.CardDb
import com.al4apps.binlib.data.models.CountryDb

@Database(
    entities = [
        CardDb::class,
        CountryDb::class,
        BankDb::class
    ],
    version = AppDatabase.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardsDao(): CardsDao

    companion object {
        const val DB_VERSION = 1
    }
}