package com.hanmajid.android.pokemonapp.data.database

import androidx.room.TypeConverter
import com.hanmajid.android.pokemonapp.model.PokemonAbility
import com.hanmajid.android.pokemonapp.model.PokemonEvolution
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

    /**
     * Converts List<[PokemonAbility]> to [String].
     */
    @TypeConverter
    fun stringToPokemonAbilityList(value: List<PokemonAbility>?): String? {
        return value?.joinToString("|") {
            it.name
        }
    }

    /**
     * Converts [String] to List<[PokemonAbility]>.
     */
    @TypeConverter
    fun pokemonAbilityListToString(value: String?): List<PokemonAbility> {
        return value?.split("|")?.map { PokemonAbility(it) } ?: emptyList()
    }

    /**
     * Converts List<[PokemonEvolution]> to [String].
     */
    @TypeConverter
    fun stringToPokemonEvolutionList(value: List<PokemonEvolution>?): String? {
        return value?.joinToString("|") {
            "${it.id}/${it.evolvesFromId}/${it.name}"
        }
    }

    /**
     * Converts [String] to List<[PokemonEvolution]>.
     */
    @TypeConverter
    fun pokemonEvolutionListToString(value: String?): List<PokemonEvolution> {
        return value?.split("|")?.map {
            val subStr = it.split("/")
            PokemonEvolution(
                id = subStr[0].toInt(),
                evolvesFromId = subStr[1].toIntOrNull(),
                name = subStr[2],
            )
        } ?: emptyList()
    }
}