package com.hanmajid.android.pokemonapp.model

import com.hanmajid.android.pokemonapp.GetPokemonDetailQuery
import com.hanmajid.android.pokemonapp.util.PokemonUtil
import com.hanmajid.android.pokemonapp.util.StringUtil

/**
 * Represents a [Pokemon]'s evolution.
 */
data class PokemonEvolution(
    /**
     * The evolution's [Pokemon.id].
     */
    val id: Int,

    /**
     * The evolution's [Pokemon.name].
     */
    val name: String,

    /**
     * The id of the [Pokemon] that it evolves from.
     */
    val evolvesFromId: Int?,
) {
    /**
     * Gets the monster's capitalized [name].
     */
    fun getNameFormatted() = StringUtil.capitalizeString(name)

    /**
     * Gets the monster's id with start padding.
     */
    fun getIdFormatted(): String {
        val paddedId = id.toString().padStart(3, '0')
        return "#$paddedId"
    }

    /**
     * Gets the official artwork image URL of the monster.
     */
    fun getOfficialArtworkImageUrl() = PokemonUtil.getOfficialArtworkImageUrl(id)

    companion object {
        /**
         * Creates [PokemonEvolution] from GraphQL json.
         */
        fun fromJson(json: GetPokemonDetailQuery.Pokemon_v2_pokemonspecy1): PokemonEvolution {
            return PokemonEvolution(
                id = json.id,
                name = json.name,
                evolvesFromId = json.evolves_from_species_id,
            )
        }
    }
}