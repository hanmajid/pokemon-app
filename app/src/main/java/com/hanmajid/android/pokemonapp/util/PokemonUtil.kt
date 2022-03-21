package com.hanmajid.android.pokemonapp.util

/**
 * Pokemon-related utility object.
 *
 * This object is needed because PokeAPI's GraphQL always returns null for sprites.
 * @see: https://github.com/PokeAPI/pokeapi/issues/614
 */
object PokemonUtil {

    /**
     * Gets the monster's id with start padding.
     */
    fun getIdFormatted(id: Int): String {
        val paddedId = id.toString().padStart(3, '0')
        return "#$paddedId"
    }

    /**
     * Gets the official artwork image URL of the monster.
     */
    fun getOfficialArtworkImageUrl(id: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    /**
     * Gets the "Pokemon Home" image URL of the monster.
     */
    fun getHomeImageUrl(id: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"

    /**
     * Gets the "Dream World" image URL of the monster.
     */
    fun getDreamWorldImageUrl(id: Int) =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"
}