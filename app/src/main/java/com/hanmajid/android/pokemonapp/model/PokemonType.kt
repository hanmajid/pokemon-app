package com.hanmajid.android.pokemonapp.model

import com.hanmajid.android.pokemonapp.GetPokemonQuery
import com.hanmajid.android.pokemonapp.R
import com.hanmajid.android.pokemonapp.util.StringUtil

/**
 * Pokemon/monster type model class.
 */
data class PokemonType(
    /**
     * The type's name in lowercase.
     */
    val name: String,
) {
    /**
     * Gets the type's capitalized [name].
     */
    fun getNameFormatted() = StringUtil.capitalizeString(name)

    /**
     * Gets pair of colors that represents the type.
     *
     * The first element of the pair is background color and the second element is the contrast/text
     * color to the background color.
     */
    fun getColorResources(): Pair<Int, Int> {
        return when (name) {
            "dragon" -> Pair(R.color.pokemon_type_dragon, R.color.white)
            "fire" -> Pair(R.color.pokemon_type_fire, R.color.white)
            "ice" -> Pair(R.color.pokemon_type_ice, R.color.white)
            "bug" -> Pair(R.color.pokemon_type_bug, R.color.white)
            "water" -> Pair(R.color.pokemon_type_water, R.color.white)
            "fighting" -> Pair(R.color.pokemon_type_fighting, R.color.white)
            "rock" -> Pair(R.color.pokemon_type_rock, R.color.white)
            "psychic" -> Pair(R.color.pokemon_type_psychic, R.color.white)
            "ghost" -> Pair(R.color.pokemon_type_ghost, R.color.white)
            "dark" -> Pair(R.color.pokemon_type_dark, R.color.white)
            "poison" -> Pair(R.color.pokemon_type_poison, R.color.white)

            "fairy" -> Pair(R.color.pokemon_type_fairy, R.color.black)
            "ground" -> Pair(R.color.pokemon_type_ground, R.color.black)
            "flying" -> Pair(R.color.pokemon_type_flying, R.color.black)
            "electric" -> Pair(R.color.pokemon_type_electric, R.color.black)
            "steel" -> Pair(R.color.pokemon_type_steel, R.color.black)
            "grass" -> Pair(R.color.pokemon_type_grass, R.color.black)
            "normal" -> Pair(R.color.pokemon_type_normal, R.color.black)
            else -> Pair(R.color.pokemon_type_normal, R.color.black)
        }
    }

    companion object {
        /**
         * Unknown monster type's name.
         */
        private const val UNKNOWN_POKEMON_TYPE = "?"

        /**
         * Creates [PokemonType] from GraphQL json.
         */
        fun fromJson(json: GetPokemonQuery.Pokemon_v2_type?): PokemonType {
            return PokemonType(
                name = json?.name ?: UNKNOWN_POKEMON_TYPE,
            )
        }
    }
}
