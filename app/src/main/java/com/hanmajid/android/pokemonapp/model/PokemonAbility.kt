package com.hanmajid.android.pokemonapp.model

import com.hanmajid.android.pokemonapp.GetPokemonDetailQuery

/**
 * Represents a [Pokemon]'s ability.
 */
data class PokemonAbility(
    /**
     * The ability name.
     */
    val name: String,
) {
    companion object {
        /**
         * Creates [PokemonAbility] from GraphQL json.
         */
        fun fromJson(json: GetPokemonDetailQuery.Pokemon_v2_pokemonability): PokemonAbility {
            return PokemonAbility(
                json.pokemon_v2_ability?.pokemon_v2_abilitynames?.first {
                    it.language_id == 9 // English language code
                }?.name ?: "-"
            )
        }
    }
}