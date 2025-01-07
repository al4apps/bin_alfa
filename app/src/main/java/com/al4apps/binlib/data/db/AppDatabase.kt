package com.al4apps.binlib.data.db

import androidx.room.Database
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
abstract class AppDatabase {

    companion object {
        const val DB_VERSION = 1
    }
}