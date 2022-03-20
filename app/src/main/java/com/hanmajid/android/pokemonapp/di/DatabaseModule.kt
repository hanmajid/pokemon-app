package com.hanmajid.android.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.hanmajid.android.pokemonapp.data.database.MainDatabase
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Database-related Dependency Injection module.
 */
@Module
class DatabaseModule {

    /**
     * Provides [MainDatabase] instance.
     */
    @Single
    fun providesMainDatabase(context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            MainDatabase.DATABASE_NAME,
        ).build()
    }
}