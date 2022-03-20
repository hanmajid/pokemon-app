package com.hanmajid.android.pokemonapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hanmajid.android.pokemonapp.data.database.dao.PokemonDao
import com.hanmajid.android.pokemonapp.model.Pokemon

/**
 * The app's main database.
 */
@Database(
    entities = [Pokemon::class],
    version = 1,
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val DATABASE_NAME = "pokemon_app.db"
    }
}