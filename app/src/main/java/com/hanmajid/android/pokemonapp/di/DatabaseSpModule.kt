package com.hanmajid.android.pokemonapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.hanmajid.android.pokemonapp.data.database.MainDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * Database & SharedPreferences-related Dependency Injection module.
 */
@Module
class DatabaseSpModule {

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

    /**
     * Provides [SharedPreferences] instance.
     */
    @Single
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "pokemon_app.sp"
        const val SP_KEY_ENABLE_CRASH_REPORTING = "com.hanmajid.android.pokemonapp.ecr"
    }
}