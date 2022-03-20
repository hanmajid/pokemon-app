package com.hanmajid.android.pokemonapp.data.database

import androidx.room.TypeConverter
import com.hanmajid.android.pokemonapp.model.PokemonType

/**
 * Room Database Type Converters.
 */
class DatabaseTypeConverters {

    /**
     * Converts List<[PokemonType]> to [String].
     */
    @TypeConverter
    fun stringToPokemonTypeList(value: List<PokemonType>?): String? {
        return value?.joinToString(" ") {
            it.name
        }
    }

    /**
     * Converts [String] to List<[PokemonType]>.
     */
    @TypeConverter
    fun pokemonTypeListToString(value: String?): List<PokemonType> {
        return value?.split(" ")?.map { PokemonType(it) } ?: emptyList()
    }
}