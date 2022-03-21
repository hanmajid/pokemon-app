package com.hanmajid.android.pokemonapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hanmajid.android.pokemonapp.GetPokemonQuery
import com.hanmajid.android.pokemonapp.util.PokemonUtil
import com.hanmajid.android.pokemonapp.util.StringUtil

/**
 * Pokemon/monster model class.
 */
@Entity(tableName = "pokemons")
data class Pokemon(
    /**
     * The monster's id.
     */
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,

    /**
     * The monster's name in lowercase.
     */
    @ColumnInfo(name = "name") val name: String,

    /**
     * Whether the monster is legendary or not.
     */
    @ColumnInfo(name = "is_legendary") val isLegendary: Boolean,

    /**
     * Whether the monster is mythical or not.
     */
    @ColumnInfo(name = "is_mythical") val isMythical: Boolean,

    /**
     * The monster's types.
     */
    @ColumnInfo(name = "types") val types: List<PokemonType>,

    /**
     * Whether the monster is in user's Favorite or not.
     */
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
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

    /**
     * Gets the "Pokemon Home" image URL of the monster.
     */
    fun getHomeImageUrl() = PokemonUtil.getHomeImageUrl(id)

    /**
     * Gets the "Dream World" image URL of the monster.
     */
    fun getDreamWorldImageUrl() = PokemonUtil.getDreamWorldImageUrl(id)

    companion object {
        /**
         * Creates [Pokemon] from GraphQL JSON.
         */
        fun fromJson(json: GetPokemonQuery.Pokemon_v2_pokemonspecy): Pokemon {
            return Pokemon(
                id = json.id,
                name = json.name,
                isLegendary = json.is_legendary,
                isMythical = json.is_mythical,
                types = json.pokemon_v2_pokemons.first().pokemon_v2_pokemontypes.map {
                    PokemonType.fromJson(it.pokemon_v2_type)
                },
                isFavorite = false,
            )
        }
    }
}