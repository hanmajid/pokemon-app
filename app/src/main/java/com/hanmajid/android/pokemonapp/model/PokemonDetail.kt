package com.hanmajid.android.pokemonapp.model

import androidx.room.ColumnInfo
import com.hanmajid.android.pokemonapp.GetPokemonDetailQuery
import com.hanmajid.android.pokemonapp.ui.detail.DetailFragment

/**
 * Represents a [Pokemon] detail information that is only fetched on [DetailFragment].
 */
data class PokemonDetail(
    /**
     * The monster's weight in hexagrams.
     */
    @ColumnInfo(name = "weight") val weight: Int? = null,

    /**
     * The monster's height in decimeters.
     */
    @ColumnInfo(name = "height") val height: Int? = null,

    /**
     * The monster's abilities.
     */
    @ColumnInfo(name = "abilities") val abilities: List<PokemonAbility>? = null,

    /**
     * The monster's evolutions.
     */
    @ColumnInfo(name = "evolutions") val evolutions: List<PokemonEvolution>? = null,
) {

    companion object {
        /**
         * Creates [PokemonEvolution] from GraphQL json.
         */
        fun fromJson(json: GetPokemonDetailQuery.Pokemon_v2_pokemonspecy): PokemonDetail {
            val pokemon = json.pokemon_v2_pokemons.first()
            return PokemonDetail(
                weight = pokemon.weight,
                height = pokemon.height,
                abilities = pokemon.pokemon_v2_pokemonabilities.map {
                    PokemonAbility.fromJson(it)
                },
                evolutions = json.pokemon_v2_evolutionchain?.pokemon_v2_pokemonspecies?.map {
                    PokemonEvolution.fromJson(it)
                },
            )
        }
    }
}